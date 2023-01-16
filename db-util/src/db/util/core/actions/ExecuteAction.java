package db.util.core.actions;

import db.util.core.Action;
import db.util.core.ConnectionDescr;
import db.util.core.JdbcUtils;
import db.util.core.ResultSetToString;
import db.util.logger.Logger;

import java.sql.Statement;

public class ExecuteAction implements Action {

    public void execute(ConnectionDescr connectionDescr, String[] sqlCommands, Logger logger) {
        if (connectionDescr == null || sqlCommands == null || logger == null)
            throw new IllegalArgumentException();
        Statement stmt = null;
        try {
            stmt = connectionDescr.getConnection().createStatement();
            for (String command : sqlCommands) {
                logger.log(command);
                if (stmt.execute(command))
                    logger.log(ResultSetToString.toString(stmt.getResultSet()));
                else
                    logger.log(stmt.getUpdateCount() + " record(s) updated");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(stmt);
        }
    }
}
