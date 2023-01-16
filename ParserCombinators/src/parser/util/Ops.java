package parser.util;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Ops {
	
	public interface Binary extends BinaryOperator<Value> {
	}

	public interface Unary extends UnaryOperator<Value> {
	}

	public static final Binary plus = (v1, v2) -> v1.plus(v2);
	public static final Binary minus = (v1, v2) -> v1.minus(v2);
	public static final Binary times = (v1, v2) -> v1.times(v2);
	public static final Binary div = (v1, v2) -> v1.div(v2);
	public static final Unary negative = (v) -> v.negative();

	public static final Binary and = (v1, v2) -> v1.and(v2);
	public static final Binary or = (v1, v2) -> v1.or(v2);
	public static final Unary not = (v) -> v.not();

	public static final Binary eq = (v1, v2) -> v1.eq(v2);
	public static final Binary ne = (v1, v2) -> v1.ne(v2);
	public static final Binary gt = (v1, v2) -> v1.gt(v2);
	public static final Binary ge = (v1, v2) -> v1.ge(v2);
	public static final Binary lt = (v1, v2) -> v1.lt(v2);
	public static final Binary le = (v1, v2) -> v1.le(v2);
	

}
