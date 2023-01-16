package db.util.core;

public class ColumnDescr {

	private final String columnName;
	private final int dataType;
	private final String typeName;
	private final int columnSize;

	public ColumnDescr(String columnName, int dataType, String typeName, int columnSize) {
		this.columnName = columnName;
		this.dataType = dataType;
		this.typeName = typeName;
		this.columnSize = columnSize;
	}

	public String getColumnName() {
		return this.columnName;
	}
	public int getDataType() {
		return this.dataType;
	}
	public String getTypeName() {
		return this.typeName;
	}
	public int getColumnSize() {
		return this.columnSize;
	}

	public String toString() {
		return this.columnName;
	}
}
