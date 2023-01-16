package db.util.core;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class SqlCommands {
    public static String[] read(Reader commandReader) {
        BufferedReader reader = new BufferedReader(commandReader);
        try {
            StringBuffer buf = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                //line = line.trim();
                if (line.length() > 0 && !line.startsWith("#")) {
                    buf.append(line);
                    buf.append('\n');
                }
                line = reader.readLine();
            }
            String[] tokens = buf.toString().split(";");
            List<String> list = new ArrayList<String>();
            for (String token : tokens) {
                token = token.trim();
                if (token.length() > 0)
                    list.add(token);
            }
            return list.toArray(new String[list.size()]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
