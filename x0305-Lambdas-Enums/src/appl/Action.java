package appl;

import java.util.function.BinaryOperator;

public enum Action {

	PLUS("Plus", (v1, v2) -> v1 + v2), 
	MINUS("Minus", (v1, v2) -> v1 - v2), 
	TIMES("Times", (v1, v2) -> v1 * v2), 
	DIV("Div", (v1, v2) -> v1 / v2);

	private final String displayName;

	private final BinaryOperator<Integer> operator;

	Action(String displayName, BinaryOperator<Integer> operator) {
		this.displayName = displayName;
		this.operator = operator;
	}

	public String displayName() {
		return this.displayName;
	}

	public BinaryOperator<Integer> getOperator() {
		return operator;
	}

}
