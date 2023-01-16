package parser.util;


@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class Value implements Comparable<Value> {

	public abstract Object value();

	private static class IntValue extends Value {
		final int v;

		IntValue(int v) {
			this.v = v;
		}

		public Object value() {
			return this.v;
		}
	}

	private static class StringValue extends Value {
		final String v;

		StringValue(String v) {
			this.v = v;
		}

		public Object value() {
			return this.v;
		}
	}

	private static class BooleanValue extends Value {
		static BooleanValue True = new BooleanValue(true);
		static BooleanValue False = new BooleanValue(false);

		static BooleanValue ofValue(boolean v) {
			return v ? True : False;
		}

		private final boolean v;

		private BooleanValue(boolean v) {
			this.v = v;
		}

		public Object value() {
			return this.v;
		}
	}

	static public Value of(Object v) {
		if (v instanceof Integer)
			return new IntValue((Integer) v);
		if (v instanceof Boolean)
			return BooleanValue.ofValue((Boolean) v);
		if (v instanceof String)
			return new StringValue((String) v);
		throw new RuntimeException("int or boolean expected; but was: " + v.getClass().getName());
	}

	static public Value valueFor(int v) {
		return new IntValue(v);
	}

	static public Value valueFor(String v) {
		return new StringValue(v);
	}

	static public Value valueFor(boolean v) {
		return BooleanValue.ofValue(v);
	}

	@Override
	public String toString() {
		return this.value().toString();
	}

	public boolean isInt() {
		return this instanceof IntValue;
	}

	public boolean isString() {
		return this instanceof StringValue;
	}

	public boolean isBoolean() {
		return this instanceof BooleanValue;
	}

	public int asInt() {
		if (this instanceof IntValue)
			return ((IntValue) this).v;
		throw new RuntimeException("illgeal call of asInt; value is " + this.getClass().getName());
	}

	public boolean asBoolean() {
		if (this instanceof BooleanValue)
			return ((BooleanValue) this).v;
		throw new RuntimeException("illgeal call of asBoolean; value is " + this.getClass().getName());
	}

	public String asString() {
		if (this instanceof StringValue)
			return ((StringValue) this).v;
		throw new RuntimeException("illgeal call of asString; value is " + this.getClass().getName());
	}

	public Value plus(Value v) {
		if (this.isInt() && v.isInt())
			return new IntValue(this.asInt() + v.asInt());
		return new StringValue(this.toString() + v.toString());
	}

	public Value minus(Value v) {
		return new IntValue(this.asInt() - v.asInt());
	}

	public Value times(Value v) {
		return new IntValue(this.asInt() * v.asInt());
	}

	public Value div(Value v) {
		return new IntValue(this.asInt() / v.asInt());
	}

	public Value negative() {
		return new IntValue(-this.asInt());
	}

	public Value and(Value v) {
		return BooleanValue.ofValue(this.asBoolean() && v.asBoolean());
	}

	public Value or(Value v) {
		return BooleanValue.ofValue(this.asBoolean() || v.asBoolean());
	}

	public Value not() {
		return BooleanValue.ofValue(!this.asBoolean());
	}

	public Value eq(Value v) {
		return BooleanValue.ofValue(this.compareTo(v) == 0);
	}

	public Value ne(Value v) {
		return BooleanValue.ofValue(this.compareTo(v) != 0);
	}

	public Value gt(Value v) {
		return BooleanValue.ofValue(this.compareTo(v) > 0);
	}

	public Value lt(Value v) {
		return BooleanValue.ofValue(this.compareTo(v) < 0);
	}

	public Value ge(Value v) {
		return BooleanValue.ofValue(this.compareTo(v) >= 0);
	}

	public Value le(Value v) {
		return BooleanValue.ofValue(this.compareTo(v) <= 0);
	}

	@Override
	public int compareTo(Value other) {
		return ((Comparable) this.value()).compareTo((Comparable) other.value());
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || ! (other instanceof Value))
			return false;
		return this.value().equals(((Value)other).value());
	}
}
