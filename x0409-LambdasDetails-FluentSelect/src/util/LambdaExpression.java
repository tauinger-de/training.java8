package util;


public class LambdaExpression {

    final Class<?> ownerClass;
    final Class<?> fieldClass;
    final String fieldName;
    final Op op;
    final Object value;

    public LambdaExpression(Class<?> ownerClass, Class<?> fieldClass, String fieldName, Op op, Object value) {
        this.ownerClass = ownerClass;
        this.fieldClass = fieldClass;
        this.fieldName = fieldName;
        this.op = op;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Entry [" + ownerClass.getName() + " " + fieldClass.getName() + " " + fieldName + " " + op + " " + value + "]";
    }
}
