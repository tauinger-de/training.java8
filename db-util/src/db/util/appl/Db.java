package db.util.appl;


import db.util.batch.Executor;
import db.util.logger.Logger;
import db.util.logger.NullLogger;
import db.util.logger.PrintStreamLogger;

public class Db {

    public static final String DB_PROPERTIES = "db.properties";
    public static final String CREATE_SQL = "create.sql";

    public static void aroundAppl() {
        around(DB_PROPERTIES, CREATE_SQL, new PrintStreamLogger(System.err));
    }

    public static void aroundTest() {
        around(DB_PROPERTIES, CREATE_SQL, new NullLogger());
    }

    public static void around(String dbPropertiesFilename, String createSqlFilename, final Logger logger) {
        Executor.execute(DB_PROPERTIES, "prepare", CREATE_SQL, logger);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.flush();
                Executor.execute(DB_PROPERTIES, "select", null, logger);
            }
        });
    }
}
