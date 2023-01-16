package db.util.batch;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import db.util.core.Action;
import db.util.core.ConnectionDescr;
import db.util.core.SqlCommands;
import db.util.core.actions.DeleteAction;
import db.util.core.actions.DropAction;
import db.util.core.actions.ExecuteAction;
import db.util.core.actions.PrepareAction;
import db.util.core.actions.SelectAction;
import db.util.logger.Logger;
import db.util.logger.NullLogger;

public class Executor {
	
	private static Map<String,Action> actions = new HashMap<String,Action>();
	static {
		actions.put("drop", new DropAction());
		actions.put("delete", new DeleteAction());
		actions.put("select", new SelectAction());
		actions.put("execute", new ExecuteAction());
		actions.put("prepare", new PrepareAction());
	}
	
	public static void execute(String propertiesFilename, String actionName, String commandsFilename, Logger logger) {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFilename));
			//properties.load(ClassLoader.getSystemResourceAsStream(propertiesFilename));
		}
		catch(Exception e) {
			throw new RuntimeException("'" + propertiesFilename + "' not found");
		}
		String[] sqlCommands = null;
		if (commandsFilename != null && commandsFilename.length() > 0) {
			try {
				Thread.currentThread().getContextClassLoader();
				//Reader commandReader = new InputStreamReader(ClassLoader.getSystemResourceAsStream((commandsFilename)));
				Reader commandReader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream((commandsFilename)));
				sqlCommands = SqlCommands.read(commandReader);
			}
			catch(Exception e) {
				throw new RuntimeException("'" + commandsFilename + "' not found");
			}
		}

		execute(properties, actionName, sqlCommands, logger);
	}

	public static void execute(Properties properties, String actionName, String[] sqlCommands, Logger logger) {
		Action action = actions.get(actionName);
		if (action == null)
			throw new IllegalArgumentException("action '" + actionName + "' not supported");
		if (logger == null)
			logger = new NullLogger();
		logger.log("");
		logger.log("======== db.util.batch.Executor ===================");
		logger.log(actionName + " [" + properties.getProperty("db.url") + "]");
		logger.log("---------------------------------------------------");

		ConnectionDescr connectionDescr = new ConnectionDescr(properties);
		try {
			action.execute(connectionDescr, sqlCommands, logger);
			connectionDescr.commit();
		}
		catch(RuntimeException e) {
			connectionDescr.rollback();
			throw e;
		}
		catch(Exception e) {
			connectionDescr.rollback();
			throw new RuntimeException(e);
		}
		finally {
			connectionDescr.close();
			logger.log("");
		}
	}
}
