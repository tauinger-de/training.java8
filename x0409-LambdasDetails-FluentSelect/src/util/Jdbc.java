package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Jdbc {

    public static int executeUpdate(Connection con, String sql, Object... args) {
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++)
                stmt.setObject(i + 1, args[i]);
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Object[]> executeQuery(Connection con, String sql, Object... args) {
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++)
                stmt.setObject(i + 1, args);
            try (ResultSet rs = stmt.executeQuery()) {
                int n = rs.getMetaData().getColumnCount();
                List<Object[]> rows = new ArrayList<>();
                while (rs.next()) {
                    Object[] row = new Object[n];
                    for (int i = 0; i < n; i++)
                        row[i] = rs.getObject(i + 1);
                    rows.add(row);
                }
                return rows;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
