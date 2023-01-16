package appl;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Operators {
	public static final BinaryOperator<Double> PLUS = (x, y) -> x + y; 
	public static final BinaryOperator<Double> MINUS = (x, y) -> x - y; 
	public static final BinaryOperator<Double> TIMES = (x, y) -> x * y; 
	public static final BinaryOperator<Double> DIV = (x, y) -> x / y; 
	public static final UnaryOperator<Double> UMINUS = x -> -x; 
}
