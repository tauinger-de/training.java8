package db.util.gui;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class ResultSetMatrix {

	private final String[] labels;
	private final List<Object[]> rows = new ArrayList<Object[]>();

	public ResultSetMatrix(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columnCount = md.getColumnCount();
		this.labels = new String[columnCount];
		for (int i = 0; i < columnCount; i++)
			this.labels[i] = md.getColumnName(i + 1);
		while (rs.next()) {
			Object[] row = new Object[columnCount];
			for (int i = 0; i < columnCount; i++)
				row[i] = rs.getObject(i + 1);
			this.rows.add(row);
		}
	}

	public String getColumnName(int columnIndex) {
		return this.labels[columnIndex];
	}

	public int getRowCount() {
		return this.rows.size();
	}

	public int getColumnCount() {
		return this.labels.length;
	}

	public Object get(int rowIndex, int columnIndex) {
		return this.rows.get(rowIndex)[columnIndex];
	}
}
