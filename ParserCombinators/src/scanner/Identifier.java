package scanner;


public class Identifier extends AbstractSymbol {

    private static final Cache<String, Identifier> cache = new Cache<String, Identifier>() {
        public Identifier createValue(String key) {
            return new Identifier(key);
        }
    };

    private final String value;

    private Identifier(String value) {
        this.value = value;
    }

    public String getAsText() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isIdentifier() {
        return true;
    }

    public Identifier asIdentifier() {
        return this;
    }

    public static Identifier get(String key) {
        return cache.lookup(key);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
