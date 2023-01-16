package db.util.core.actions;

import db.util.core.Action;
import db.util.core.ConnectionDescr;
import db.util.core.JdbcUtils;
import db.util.core.TableDescr;
import db.util.logger.Logger;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DropAction implements Action {

    public void execute(ConnectionDescr connectionDescr, String[] sqlCommands, Logger logger) {
        if (connectionDescr == null || logger == null)
            throw new IllegalArgumentException();
        List<String> droppedTables = new ArrayList<String>();
        int n = connectionDescr.getTableDescrCount();
        for (int i = 0; i <= n && droppedTables.size() < n; i++) {
            for (int j = 0; j < connectionDescr.getTableDescrCount(); j++) {
                TableDescr tableDescr = connectionDescr.getTableDescr(j);
                if (droppedTables.contains(tableDescr.getTableName()))
                    continue;
                Statement stmt = null;
                try {
                    stmt = connectionDescr.getConnection().createStatement();
                    String sql = "drop table " + tableDescr;
                    stmt.executeUpdate(sql);
                    logger.log(sql);
                    droppedTables.add(tableDescr.getTableName());
                } catch (Exception e) {
                    try {
                        String sql = "drop view " + tableDescr;
                        stmt.executeUpdate(sql);
                        logger.log(sql);
                        droppedTables.add(tableDescr.getTableName());
                    } catch (Exception ex) {
                        // this is okay
                    }
                } finally {
                    JdbcUtils.close(stmt);
                }
            }
        }
        if (droppedTables.size() != n)
            logger.log("\n" + (n - droppedTables.size()) + " table(s) not dropped");
        else
            logger.log("\nall tables dropped!\n");
    }
}
