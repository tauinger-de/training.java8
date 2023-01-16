package util;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Where<T> extends Query<T> {
    public final Select<T> select;
    public final LambdaExpression lambdaEntry;

    public Where(Select<T> select, SPredicate<T> predicate) {
        this.select = select;
        final SerializedLambda l = LambdaUtil.getSerializedLambda(predicate);
        this.lambdaEntry = select.from.lambdaEntries.get(l.getImplMethodName());
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(this.select);
        buf.append(" where ");
        final LambdaExpression e = this.lambdaEntry;
        final Object value = e.value instanceof String ? "'" + e.value + "'" : e.value;
        buf.append(e.fieldName + " " + ops.get(e.op) + " " + value);
        return buf.toString();
    }

    public Where<T> log() {
        System.out.println(this);
        return this;
    }

    private static Map<Op, String> ops = new HashMap<>();

    static {
        ops.put(Op.EQ, "=");
        ops.put(Op.NE, "!=");
        ops.put(Op.GT, ">");
        ops.put(Op.LT, "<");
        ops.put(Op.GE, ">=");
        ops.put(Op.LE, "<=");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> list() {
        List<?> table = (List<?>) Query.database.getList(this.select.from.tableClass.getSimpleName());
        List<Object> selectedObjects = new ArrayList<>();
        try {
            final Class<?> tableClass = this.select.from.tableClass;
            String fieldName = this.lambdaEntry.fieldName;
            Field field = tableClass.getField(fieldName);
            for (Object obj : table) {
                Object value = field.get(obj);
                if (this.lambdaEntry.op.predicate.test(value, this.lambdaEntry.value))
                    selectedObjects.add(obj);
            }
            return this.select.createList(selectedObjects, tableClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
