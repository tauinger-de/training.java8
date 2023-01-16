package db.util.core.actions;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import db.util.core.Action;
import db.util.core.ConnectionDescr;
import db.util.core.JdbcUtils;
import db.util.core.TableDescr;
import db.util.logger.Logger;

public class DeleteAction implements Action {

	public void execute(ConnectionDescr connectionDescr, String[] sqlCommands, Logger logger) {
		if (connectionDescr == null || logger == null)
			throw new IllegalArgumentException();
		List<String> deletedTables = new ArrayList<String>();
		int n = connectionDescr.getTableDescrCount();
		for (int i = 0; i <= n && deletedTables.size() < n; i++) {
			for (int j = 0; j < connectionDescr.getTableDescrCount(); j++) {
				TableDescr tableInfo = connectionDescr.getTableDescr(j);
				if (deletedTables.contains(tableInfo.toString()))
					continue;
				Statement stmt = null;
				ResultSet rs = null;
				try {
					stmt = connectionDescr.getConnection().createStatement();
					rs = stmt.executeQuery("select count(*) from " + tableInfo);
					rs.next();
					int nRecords = rs.getInt(1);
					String sql = "delete from " + tableInfo;
					logger.log(sql);
					int result = stmt.executeUpdate(sql);
					if (nRecords == result) {
						deletedTables.add(tableInfo.toString());
					}
				}
				catch (Exception e) {
				}
				finally {
					JdbcUtils.close(stmt, rs);
				}
			}
		}
		if (deletedTables.size() != n)
			logger.log("\n" + (n - deletedTables.size()) + " table(s) not clean");
		else
			logger.log("\nall rows of all tables deleted\n");
	}
}
