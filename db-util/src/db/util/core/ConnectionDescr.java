package db.util.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class ConnectionDescr implements Iterable<TableDescr> {

    private final Properties properties;
    private final Connection con;
    private final List<TableDescr> tableDescrList = new ArrayList<TableDescr>();

    public ConnectionDescr(Properties properties) {
        this.properties = properties;

        String driver = properties.getProperty("db.driver");
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user", "");
        String password = properties.getProperty("db.password", "");
        String catalog = properties.getProperty("db.catalog");
        String schema = properties.getProperty("db.schema");

        ResultSet rs = null;
        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            DatabaseMetaData md = con.getMetaData();
            rs = md.getColumns(catalog, schema, "%", "%");
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                TableDescr tableDescr = this.getTableDescr(tableName);
                if (tableDescr == null) {
                    tableDescr = new TableDescr(tableName);
                    this.tableDescrList.add(tableDescr);
                }
                ColumnDescr columnDescr = new ColumnDescr(rs.getString("COLUMN_NAME"), rs.getInt("DATA_TYPE"), rs
                        .getString("TYPE_NAME"), rs.getInt("COLUMN_SIZE"));
                tableDescr.add(columnDescr);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(null, null, rs);
        }

    }

    public void commit() {
        JdbcUtils.commit(this.con);
    }

    public void rollback() {
        JdbcUtils.rollback(this.con);
    }

    public void close() {
        JdbcUtils.close(this.con);
    }

    public Properties getProperties() {
        return this.properties;
    }

    public Connection getConnection() {
        return this.con;
    }

    public int getTableDescrCount() {
        return this.tableDescrList.size();
    }

    public TableDescr getTableDescr(int index) {
        return (TableDescr) this.tableDescrList.get(index);
    }

    public TableDescr getTableDescr(String tableName) {
        for (TableDescr tableDescr : this.tableDescrList) {
            if (tableDescr.tableName.equalsIgnoreCase(tableName))
                return tableDescr;
        }
        return null;
    }

    public Iterator<TableDescr> iterator() {
        return this.tableDescrList.iterator();
    }

    public String toString() {
        return "Database";
    }
}
