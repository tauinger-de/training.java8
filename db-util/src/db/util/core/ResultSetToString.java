package db.util.core;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class ResultSetToString {

    public static String toString(ResultSet rs) {
        try {
            StringBuilder buf = new StringBuilder();
            ResultSetMetaData md = rs.getMetaData();
            int n = md.getColumnCount();
            List<Object[]> rows = new ArrayList<Object[]>();
            int[] width = new int[n];
            for (int i = 0; i < n; i++)
                width[i] = md.getColumnName(i + 1).length();
            while (rs.next()) {
                Object[] row = new Object[n];
                rows.add(row);
                for (int i = 0; i < md.getColumnCount(); i++) {
                    Object obj = rs.getObject(i + 1);
                    row[i] = obj;
                    int l = obj == null ? 0 : obj.toString().length();
                    if (l > width[i])
                        width[i] = l;
                }
            }

            for (int i = 0; i < n; i++)
                append(buf, md.getColumnName(i + 1), width[i]);
            buf.append('\n');
            for (int i = 0; i < n; i++)
                append(buf, width[i]);
            buf.append('\n');

            for (Object[] row : rows) {
                for (int j = 0; j < n; j++) {
                    Object obj = row[j];
                    if (obj == null)
                        obj = "NULL";
                    append(buf, obj.toString(), width[j]);
                }
                buf.append('\n');
            }
            for (int i = 0; i < n; i++)
                append(buf, width[i]);
            buf.append('\n');
            return buf.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void append(StringBuilder buf, String s, int width) throws IOException {
        buf.append(s);
        int n = width - s.length() + 1;
        while (n-- > 0)
            buf.append(' ');
    }

    private static void append(StringBuilder buf, int width) throws IOException {
        int n = width + 1;
        while (n-- > 0)
            buf.append('-');
    }

}
