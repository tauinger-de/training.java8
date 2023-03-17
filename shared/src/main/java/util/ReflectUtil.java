package util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReflectUtil {
    public static String stringOf(Object obj) {
        Class<?> cls = obj.getClass();
        Map<String, Object> values = new LinkedHashMap<>();
        try {
            for (PropertyDescriptor pd : Introspector.getBeanInfo(cls, Object.class).getPropertyDescriptors()) {
                values.put(pd.getName(), pd.getReadMethod().invoke(obj));
            }
            for (Field f : cls.getFields()) {
                if (values.get(f.getName()) == null) {
                    values.put(f.getName(), f.get(obj));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        StringBuilder buf = new StringBuilder();
        buf.append(cls.getSimpleName()).append("[");
        boolean first = true;
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            if (first)
                first = false;
            else
                buf.append(", ");
            buf.append(entry.getKey()).append("=").append(entry.getValue());
        }
        buf.append("]");
        return buf.toString();

    }
}
