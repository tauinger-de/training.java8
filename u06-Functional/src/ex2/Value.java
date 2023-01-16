package ex2;

import java.util.function.DoubleBinaryOperator;

public abstract class Value {
    static private class SimpleValue extends Value {
        final double value;

        SimpleValue(double value) {
            this.value = value;
        }

        public double eval() {
            return this.value;
        }
    }

    static private class ValuePair extends Value {
        final Value left;
        final Value right;
        final DoubleBinaryOperator op;

        public ValuePair(Value left, Value right, DoubleBinaryOperator op) {
            this.left = left;
            this.right = right;
            this.op = op;
        }

        public double eval() {
            return this.op.applyAsDouble(left.eval(), right.eval());
        }
    }

    public Value plus(double v) {
        return plus($(v));
    }

    public Value minus(double v) {
        return minus($(v));
    }

    public Value times(double v) {
        return times($(v));
    }

    public Value div(double v) {
        return div($(v));
    }

    public Value plus(Value v) {
        return new ValuePair(this, v, (x, y) -> x + y);
    }

    public Value minus(Value v) {
        return new ValuePair(this, v, (x, y) -> x - y);
    }

    public Value times(Value v) {
        return new ValuePair(this, v, (x, y) -> x * y);
    }

    public Value div(Value v) {
        return new ValuePair(this, v, (x, y) -> x / y);
    }

    public static Value $(double v) {
        return new SimpleValue(v);
    }

    public abstract double eval();
}
