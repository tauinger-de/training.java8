package db.util.gui;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;


import db.util.core.ColumnDescr;
import db.util.core.ConnectionDescr;
import db.util.core.JdbcUtils;
import db.util.core.SqlCommands;
import db.util.core.TableDescr;
import db.util.core.actions.DeleteAction;
import db.util.core.actions.DropAction;
import db.util.logger.Logger;

class Controller {

	private final BeforeAfter beforeAfter = new BeforeAfter() {
		public void before(String command) {
			Controller.this.mainPanel.messageField.setText("");
		}
		public void after(String command) {
			if (Controller.this.connectionDescr != null)
				Controller.this.connectionDescr.commit();
			Controller.this.stateChanged();
		}
		public void after(String command, Throwable ex) {
			if (Controller.this.connectionDescr != null)
				Controller.this.connectionDescr.rollback();
			Controller.this.mainPanel.messageField.setText(ex.toString());
			Controller.this.stateChanged();
		}
	};

	private final Logger logger = new Logger() {
		public void log(String message) {
			Controller.this.mainPanel.commandArea.append(message + "\n");
		}
	};

	private ConnectionDescr connectionDescr;

	private CommandHistory history = new CommandHistory();

	private final DefaultTableModel emptyTableModel = new DefaultTableModel();

	private final DefaultTreeModel emptyTreeModel = new DefaultTreeModel(null);

	private final MainPanel mainPanel;

	public Controller(MainPanel mainPanel, Properties props) throws Exception {
		this.mainPanel = mainPanel;
		this.mainPanel.commandPanel.initialize(this.beforeAfter, this, "connect", null, "execute", null,
				"dropAllTables", "deleteAll", null, "loadScript", null, "clear", "prev", "next", null, "exit");
		this.mainPanel.textFieldPanel.initialize("db.driver", "db.url", "db.user", "db.password", "db.schema",
				"db.catalog");
		this.mainPanel.textFieldPanel.setProperties(props);
		this.mainPanel.tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				Controller.this.select(e.getPath());
			}
		});
		this.stateChanged();
	}

	public void exit() {
		if (this.connectionDescr != null)
			this.connectionDescr.close();
		System.exit(0);
	}

	public void connect() {
		if (this.connectionDescr != null)
			this.connectionDescr.close();
		Properties props = this.mainPanel.textFieldPanel.getProperties();
		this.connectionDescr = new ConnectionDescr(props);
		this.mainPanel.tree.setModel(new ConnectionDescrTreeModel(this.connectionDescr));
	}

	public void execute() {
		if (this.connectionDescr == null)
			return;
		String text = this.mainPanel.commandArea.getText().trim();
		String[] commands = SqlCommands.read(new StringReader(text));
		for (String command : commands) {
			Statement stmt = null;
			ResultSet rs = null;
			try {
				stmt = this.connectionDescr.getConnection().createStatement();
				if (stmt.execute(command)) {
					rs = stmt.getResultSet();
					this.mainPanel.table.setModel(new ResultSetMatrixTableModel(new ResultSetMatrix(rs)));
				}
				else
					this.mainPanel.table.setModel(this.emptyTableModel);
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
			finally {
				JdbcUtils.close(stmt, rs);
			}
		}
		this.history.add(text);
	}

	public void clear() {
		this.mainPanel.commandArea.setText("");
		this.mainPanel.table.setModel(this.emptyTableModel);
	}

	public void prev() {
		this.mainPanel.commandArea.setText(this.history.prev());
	}

	public void next() {
		this.mainPanel.commandArea.setText(this.history.next());
	}

	public void dropAllTables() {
		if (!this.confirm("Do you really want to drop all tables?"))
			return;
		if (this.connectionDescr == null)
			return;
		this.clear();
		new DropAction().execute(this.connectionDescr, null, logger);
		this.connect();
	}

	public void deleteAll() {
		if (!this.confirm("Do you really want to delete all rows of all tables?"))
			return;
		if (this.connectionDescr == null)
			return;
		this.clear();
		new DeleteAction().execute(this.connectionDescr, null, this.logger);
		this.connect();
	}

	public void loadScript() {
		// JFileChooser doesn't work....
		FileDialog fileDialog = new FileDialog((Frame) null);

		fileDialog.setFilenameFilter(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".sql");
			}
		});
		fileDialog.setDirectory(".");
		fileDialog.setVisible(true);
		String dir = fileDialog.getDirectory();
		String file = fileDialog.getFile();
		if (file == null)
			return;
		String filename = dir + "/" + file;

		this.mainPanel.commandArea.setText("");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String line = reader.readLine();
			while (line != null) {
				this.mainPanel.commandArea.append(line);
				this.mainPanel.commandArea.append("\n");
				line = reader.readLine();
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (Exception e) {
				}
			}
		}
	}

	private void select(TreePath path) {
		if (path == null || path.getPathCount() < 2)
			return;
		TableDescr tableInfo = (TableDescr) path.getPathComponent(1);
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.connectionDescr.getConnection().createStatement();
			String sql = "select * from " + tableInfo;
			if (path.getPathCount() == 3) {
				ColumnDescr columnInfo = (ColumnDescr) path.getPathComponent(2);
				sql += (" order by " + columnInfo);
			}
			rs = stmt.executeQuery(sql);
			this.mainPanel.table.setModel(new ResultSetMatrixTableModel(new ResultSetMatrix(rs)));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			JdbcUtils.close(stmt, rs);
		}
	}

	private void stateChanged() {
		if (this.connectionDescr == null) {
			this.mainPanel.table.setModel(this.emptyTableModel);
			this.mainPanel.tree.setModel(this.emptyTreeModel);
		}
		this.mainPanel.commandPanel.getButton("execute").setEnabled(this.connectionDescr != null);
		this.mainPanel.commandPanel.getButton("dropAllTables").setEnabled(this.connectionDescr != null);
		this.mainPanel.commandPanel.getButton("deleteAll").setEnabled(this.connectionDescr != null);
		this.mainPanel.commandPanel.getButton("loadScript").setEnabled(this.connectionDescr != null);
		this.mainPanel.commandPanel.getButton("prev")
				.setEnabled(this.connectionDescr != null && this.history.hasPrev());
		this.mainPanel.commandPanel.getButton("next")
				.setEnabled(this.connectionDescr != null && this.history.hasNext());
		this.mainPanel.commandArea.requestFocus();
	}

	private boolean confirm(String message) {
		int result = JOptionPane.showConfirmDialog(this.mainPanel, message, "Confirm", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		return result == JOptionPane.OK_OPTION;
	}
}
