package appl;

public class NumberExpression extends Expression {

    private final double value;

    public NumberExpression(double value) {
        this.value = value;
    }

    public double evaluate() {
        return this.value;
    }
}
