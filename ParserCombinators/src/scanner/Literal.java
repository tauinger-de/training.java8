package scanner;


public class Literal extends AbstractSymbol {

    private static final Cache<Object, Literal> cache = new Cache<Object, Literal>() {
        public Literal createValue(Object key) {
            return new Literal(key);
        }
    };

    private final Object value;

    private Literal(Object value) {
        this.value = value;
    }

    public String getAsText() {
        return this.value.toString();
    }

    public boolean isLiteral() {
        return true;
    }

    public Literal asLiteral() {
        return this;
    }

    public static Literal get(byte key) {
        return cache.lookup(key);
    }

    public static Literal get(short key) {
        return cache.lookup(key);
    }

    public static Literal get(int key) {
        return cache.lookup(key);
    }

    public static Literal get(long key) {
        return cache.lookup(key);
    }

    public static Literal get(float key) {
        return cache.lookup(key);
    }

    public static Literal get(double key) {
        return cache.lookup(key);
    }

    public static Literal get(char key) {
        return cache.lookup(key);
    }

    public static Literal get(boolean key) {
        return cache.lookup(key);
    }

    public static Literal get(String key) {
        return cache.lookup(key);
    }

    public Class<?> getType() {
        return this.value.getClass();
    }

    public Object getValue() {
        return this.value;
    }

    public boolean isByte() {
        return this.value instanceof Byte;
    }

    public byte byteValue() {
        if (this.isByte())
            return (Byte) this.value;
        throw new RuntimeException();
    }

    public boolean isShort() {
        return this.value instanceof Short;
    }

    public short shortValue() {
        if (this.isShort())
            return (Short) this.value;
        throw new RuntimeException();
    }

    public boolean isInt() {
        return this.value instanceof Integer;
    }

    public int intValue() {
        if (this.isInt())
            return (Integer) this.value;
        throw new RuntimeException();
    }

    public boolean isLong() {
        return this.value instanceof Long;
    }

    public long longValue() {
        if (this.isLong())
            return (Long) this.value;
        throw new RuntimeException();
    }

    public boolean isFloat() {
        return this.value instanceof Float;
    }

    public float floatValue() {
        if (this.isFloat())
            return (Float) this.value;
        throw new RuntimeException();
    }

    public boolean isDouble() {
        return this.value instanceof Double;
    }

    public double doubleValue() {
        if (this.isDouble())
            return (Double) this.value;
        throw new RuntimeException();
    }

    public boolean isChar() {
        return this.value instanceof Character;
    }

    public char charValue() {
        if (this.isChar())
            return (Character) this.value;
        throw new RuntimeException();
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    public boolean booleanValue() {
        if (this.isBoolean())
            return (Boolean) this.value;
        throw new RuntimeException();
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    public String stringValue() {
        if (this.isString())
            return (String) this.value;
        throw new RuntimeException();
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
