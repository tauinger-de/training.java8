package db.util.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcUtils {
    public static void commit(Connection con) {
        try {
            con.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback(Connection con) {
        try {
            con.rollback();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection con) {
        close(con, null, null);
    }

    public static void close(Statement stmt) {
        close(null, stmt, null);
    }

    public static void close(ResultSet rs) {
        close(null, null, rs);
    }

    public static void close(Connection con, Statement stmt) {
        close(con, stmt, null);
    }

    public static void close(Statement stmt, ResultSet rs) {
        close(null, stmt, rs);
    }

    public static void close(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
            }
        }
    }

}
