package db.util.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;

class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public final CommandPanel commandPanel = new CommandPanel();
	public final TextFieldPanel textFieldPanel = new TextFieldPanel(30);
	public final JTree tree = new JTree();
	public final JTextArea commandArea = new JTextArea();
	public final JTable table = new JTable();
	public final JTextField messageField = new JTextField();
	
	public MainPanel() {
		JScrollPane scrollTextFieldPanel = new JScrollPane(this.textFieldPanel);
		JScrollPane scrollCommandArea = new JScrollPane(this.commandArea);
		JScrollPane scrollTree = new JScrollPane(this.tree);
		JScrollPane scrollTable = new JScrollPane(this.table);
		scrollCommandArea.setPreferredSize(new Dimension(0, 250));
		scrollTable.setPreferredSize(new Dimension(0, 0));
		JSplitPane splitPaneWest = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollTextFieldPanel, scrollTree);
		JSplitPane splitPaneEast = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollCommandArea, scrollTable);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneWest, splitPaneEast);
		this.setLayout(new BorderLayout());
		this.add(this.commandPanel, BorderLayout.NORTH);
		this.add(splitPane, BorderLayout.CENTER);
		this.add(this.messageField, BorderLayout.SOUTH);
		this.messageField.setForeground(Color.red);
	}
}

