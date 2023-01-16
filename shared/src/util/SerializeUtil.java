package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {

    @SuppressWarnings("unchecked")
    public static <T> T serializeDeserialize(T obj) {
        return (T) deserialize(serialize(obj));
    }

    public static ByteArrayOutputStream serialize(Object obj) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (final ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    public static Object deserialize(ByteArrayOutputStream out) {
        final ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        try (final ObjectInputStream ois = new ObjectInputStream(in)) {
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
