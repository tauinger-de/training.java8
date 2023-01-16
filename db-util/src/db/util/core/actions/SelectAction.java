package db.util.core.actions;

import db.util.core.*;
import db.util.logger.Logger;

import java.sql.ResultSet;
import java.sql.Statement;

public class SelectAction implements Action {

    public void execute(ConnectionDescr connectionDescr, String[] sqlCommands, Logger logger) {
        if (connectionDescr == null || logger == null)
            throw new IllegalArgumentException();
        Statement stmt = null;
        try {
            stmt = connectionDescr.getConnection().createStatement();
            for (TableDescr tableDescr : connectionDescr) {
                ResultSet rs = stmt.executeQuery("select * from " + tableDescr.getTableName());
                logger.log(tableDescr.getTableName());
                logger.log(ResultSetToString.toString(rs));
                JdbcUtils.close(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(stmt);
        }
    }
}
