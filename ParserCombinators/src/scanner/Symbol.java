package scanner;

public interface Symbol {
    public abstract String getAsText();

    public abstract boolean isLiteral();

    public abstract Literal asLiteral();

    public abstract boolean isIdentifier();

    public abstract Identifier asIdentifier();
}
