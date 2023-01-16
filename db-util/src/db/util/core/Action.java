package db.util.core;

import db.util.logger.Logger;

public interface Action {
    public void execute(ConnectionDescr connectionDescr, String[] sqlCommands, Logger logger);
}
