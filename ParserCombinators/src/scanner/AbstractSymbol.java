package scanner;

public abstract class AbstractSymbol implements Symbol {
    public boolean isLiteral() {
        return false;
    }

    public Literal asLiteral() {
        throw new RuntimeException("Symbol isn't a Constant");
    }

    public boolean isIdentifier() {
        return false;
    }

    public Identifier asIdentifier() {
        throw new RuntimeException("Symbol isn't an Identifier");
    }
}
