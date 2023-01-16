package appl;

import java.util.function.BinaryOperator;

public class BinaryExpression extends Expression {
    private final BinaryOperator<Double> op;
    private final Expression left;
    private final Expression right;

    public BinaryExpression(BinaryOperator<Double> op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public double evaluate() {
        return op.apply(this.left.evaluate(), this.right.evaluate());
    }
}
