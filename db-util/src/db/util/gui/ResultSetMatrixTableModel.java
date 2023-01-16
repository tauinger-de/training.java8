package db.util.gui;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

class ResultSetMatrixTableModel implements TableModel {
    private final ResultSetMatrix matrix;

    public ResultSetMatrixTableModel(ResultSetMatrix matrix) {
        this.matrix = matrix;
    }

    public int getRowCount() {
        return this.matrix.getRowCount();
    }

    public int getColumnCount() {
        return this.matrix.getColumnCount();
    }

    public String getColumnName(int columnIndex) {
        return this.matrix.getColumnName(columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.matrix.get(rowIndex, columnIndex);
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public void addTableModelListener(TableModelListener l) {
    }

    public void removeTableModelListener(TableModelListener l) {
    }

}
