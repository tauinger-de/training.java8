package db.util.batch;

import db.util.logger.Logger;
import db.util.logger.PrintStreamLogger;

public class Main {
	
	public static void main(String[] args) {

		if (args.length < 2) 
			throw new RuntimeException("illegal arguments for main-Method");

		String propertiesFilename = args[0];
		String actionName = args[1];
		String commandsFilename = args.length > 2 ? args[2] : null;
		Logger logger = new PrintStreamLogger();

		Executor.execute(propertiesFilename, actionName, commandsFilename, logger);
	}
}
