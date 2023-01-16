package db.util.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TableDescr implements Iterable<ColumnDescr> {

    public final String tableName;
    private final List<ColumnDescr> columnDescrList = new ArrayList<ColumnDescr>();

    public TableDescr(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void add(ColumnDescr info) {
        this.columnDescrList.add(info);
    }

    public int getColumnDescrCount() {
        return this.columnDescrList.size();
    }

    public ColumnDescr getColumnDescr(int index) {
        return (ColumnDescr) this.columnDescrList.get(index);
    }

    public ColumnDescr getColumnDescr(String columnName) {
        for (ColumnDescr columnDescr : this.columnDescrList) {
            if (columnDescr.getColumnName().equalsIgnoreCase(columnName))
                return columnDescr;
        }
        return null;
    }

    public Iterator<ColumnDescr> iterator() {
        return this.columnDescrList.iterator();
    }

    public String toString() {
        return this.tableName;
    }
}
