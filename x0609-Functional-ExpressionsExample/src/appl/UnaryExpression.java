package appl;

import java.util.function.UnaryOperator;

public class UnaryExpression extends Expression {
	private final UnaryOperator<Double> op;
	private final Expression inner;
	public UnaryExpression(UnaryOperator<Double> op, Expression inner) {
		this.op = op;
		this.inner = inner;
	}
	public double evaluate() {
		return op.apply(this.inner.evaluate());
	}
}
