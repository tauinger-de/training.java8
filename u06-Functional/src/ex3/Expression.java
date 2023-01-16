package ex3;

@FunctionalInterface
public interface Expression {
    public abstract double eval();

    public static Expression $(double v) {
        return () -> v;
    }

    public default Expression plus(Expression e) {
        return () -> this.eval() + e.eval();
    }

    public default Expression minus(Expression e) {
        return () -> this.eval() - e.eval();
    }

    public default Expression times(Expression e) {
        return () -> this.eval() * e.eval();
    }

    public default Expression div(Expression e) {
        return () -> this.eval() / e.eval();
    }

    public default Expression minus() {
        return () -> -this.eval();
    }

    public default Expression plus() {
        return () -> +this.eval();
    }
}
