package db.util.gui;

import db.util.core.ColumnDescr;
import db.util.core.ConnectionDescr;
import db.util.core.TableDescr;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

class ConnectionDescrTreeModel implements TreeModel {
    private final ConnectionDescr connectionDescr;

    public ConnectionDescrTreeModel(ConnectionDescr databaseDescr) {
        this.connectionDescr = databaseDescr;
    }

    public Object getRoot() {
        return this.connectionDescr;
    }

    public int getChildCount(Object parent) {
        if (parent instanceof ConnectionDescr)
            return ((ConnectionDescr) parent).getTableDescrCount();
        if (parent instanceof TableDescr)
            return ((TableDescr) parent).getColumnDescrCount();
        if (parent instanceof ColumnDescr)
            return 2;
        return 0;
    }

    public Object getChild(Object parent, int index) {
        if (parent instanceof ConnectionDescr)
            return ((ConnectionDescr) parent).getTableDescr(index);
        if (parent instanceof TableDescr)
            return ((TableDescr) parent).getColumnDescr(index);
        if (parent instanceof ColumnDescr) {
            switch (index) {
                case 0:
                    return ((ColumnDescr) parent).getTypeName();
                case 1:
                    return "Size = " + ((ColumnDescr) parent).getColumnSize();
                default:
                    return null;
            }
        }
        return null;
    }

    public boolean isLeaf(Object node) {
        if (node instanceof ConnectionDescr)
            return false;
        if (node instanceof TableDescr)
            return false;
        if (node instanceof ColumnDescr)
            return false;
        return true;
    }

    public int getIndexOfChild(Object parent, Object child) {
        int n = this.getChildCount(parent);
        for (int i = 0; i < n; i++)
            if (this.getChild(parent, i) == child)
                return i;
        return -1;
    }

    public void valueForPathChanged(TreePath path, Object value) {
    }

    public void addTreeModelListener(TreeModelListener l) {
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }
}
