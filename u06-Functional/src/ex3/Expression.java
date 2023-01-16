package ex3;

@FunctionalInterface
public interface Expression {

    double eval();

    static Expression $(double v) {
        return () -> v;
    }

    default Expression plus(Expression e) {
        return () -> this.eval() + e.eval();
    }

    default Expression minus(Expression e) {
        return () -> this.eval() - e.eval();
    }

    default Expression times(Expression e) {
        return () -> this.eval() * e.eval();
    }

    default Expression div(Expression e) {
        return () -> this.eval() / e.eval();
    }

    default Expression minus() {
        return () -> -this.eval();
    }

    default Expression plus() {
        return () -> +this.eval();
    }

}
