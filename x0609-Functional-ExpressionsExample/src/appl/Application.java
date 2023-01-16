package appl;

import static appl.Operators.*;

public class Application {

	public static void main(String[] args) throws Exception {
		
		Expression e1 = new NumberExpression(2);
		Expression e2 = new NumberExpression(10);
		UnaryExpression e3 = new UnaryExpression(UMINUS, e1);
		BinaryExpression e = new BinaryExpression(PLUS, e2, e3);
		System.out.println(e.evaluate());
	}		
}
