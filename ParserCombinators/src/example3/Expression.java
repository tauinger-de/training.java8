package example3;

import parser.util.Value;
import parser.util.Ops.Binary;
import parser.util.Ops.Unary;

public abstract class Expression {
	
	public abstract Value evaluate(Context context);
	
	public static Expression of(int v) {
		return new ValueExpression(Value.valueFor(v));
	}
	public static Expression of(String v) {
		return new ValueExpression(Value.valueFor(v));
	}
	public static Expression of(boolean v) {
		return new ValueExpression(Value.valueFor(v));
	}
	public static Expression variable(String name) {
		return new VariableExpression(name);
	}
	public static Expression unary(Unary op, Expression e) {
		return new UnaryExpression(op, e);
	}
	public static Expression binary(Binary op, Expression left, Expression right) {
		return new BinaryExpression(op, left, right);
	}
	
	public static class ValueExpression extends Expression {
		public final Value value;
		public ValueExpression(Value value) {
			this.value = value;
		}
		public Value evaluate(Context context) {
			return this.value;
		}
	}
	
	public static class VariableExpression extends Expression {
		public final String name;
		public VariableExpression(String name) {
			this.name = name;
		}
		public Value evaluate(Context context) {
			return context.getVariable(this.name);
		}
	}
	
	public static class UnaryExpression extends Expression {
		public final Unary op;
		public final Expression inner;
		public UnaryExpression(Unary op, Expression inner) {
			this.op = op;
			this.inner = inner;
		}
		public Value evaluate(Context context) {
			return this.op.apply(this.inner.evaluate(context));
		}
	}
	
	public static class BinaryExpression extends Expression {
		public final Binary op;
		public final Expression left;
		public final Expression right;
		public BinaryExpression(Binary op, Expression left, Expression right) {
			this.op = op;
			this.left = left;
			this.right = right;
		}
		public Value evaluate(Context context) {
			return op.apply(this.left.evaluate(context), this.right.evaluate(context));
		}
	}
}
