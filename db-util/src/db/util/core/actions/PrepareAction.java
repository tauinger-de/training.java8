package db.util.core.actions;


import db.util.core.Action;
import db.util.core.ConnectionDescr;
import db.util.logger.Logger;

public class PrepareAction implements Action {

	public void execute(ConnectionDescr connectionDescr, String[] sqlCommands, Logger logger) {
		if (connectionDescr == null || sqlCommands == null || logger == null)
			throw new IllegalArgumentException();
		new DropAction().execute(connectionDescr, sqlCommands, logger);
		new ExecuteAction().execute(connectionDescr, sqlCommands, logger);
	}
}
