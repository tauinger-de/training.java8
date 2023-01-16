package example3;

import java.util.Objects;

import parser.util.Value;

public abstract class Statement {
	
	public abstract void execute(Context context);
	
	public static Statement createAssignment(String varName, Expression expression) {
		return new Assignment(varName, expression);
	}
	public static Statement createPrint(Expression expression) {
		return new PrintStatement(expression); 
	}
	public static Statement createIf(Expression expression, Statement thenStatement, Statement elseStatement) {
		return new IfStatement(expression, thenStatement, elseStatement); 
	}
	public static Statement createWhile(Expression expression, Statement statement) {
		return new WhileStatement(expression, statement); 
	}
	public static Statement createPair(Statement left, Statement right) {
		return new StatementPair(left, right); 
	}
	
	public static class Assignment extends Statement {
		public final String varName;
		public final Expression expression;
		public Assignment(String varName, Expression expression) {
			Objects.requireNonNull(varName);
			Objects.requireNonNull(expression);
			this.varName = varName;
			this.expression = expression;
		}
		public void execute(Context context) {
			context.setVariable(varName, expression.evaluate(context));
		}
	}
	
	public static class PrintStatement extends Statement {
		public final Expression expression;
		public PrintStatement(Expression expression) {
			Objects.requireNonNull(expression);
			this.expression = expression;
		}
		public void execute(Context context) {
			context.ps.println(this.expression.evaluate(context));
		}
	}
	
	public static class IfStatement extends Statement {
		public final Expression expression;
		public final Statement thenStatement;
		public final Statement elseStatement;
		public IfStatement(Expression expression, Statement thenStatement, Statement elseStatement) {
			Objects.requireNonNull(expression);
			Objects.requireNonNull(thenStatement);
			this.expression = expression;
			this.thenStatement = thenStatement;
			this.elseStatement = elseStatement;
		}
		public void execute(Context context) {
			if (expression.evaluate(context) == Value.of(true))
				this.thenStatement.execute(context);
			else if (this.elseStatement != null)
				this.elseStatement.execute(context);
		}
	}

	public static class WhileStatement extends Statement {
		public final Expression expression;
		public final Statement statement;
		public WhileStatement(Expression expression, Statement statement) {
			Objects.requireNonNull(expression);
			Objects.requireNonNull(statement);
			this.expression = expression;
			this.statement = statement;
		}
		public void execute(Context context) {
			while (expression.evaluate(context) == Value.of(true))
				this.statement.execute(context);
		}
	}

	public static class StatementPair extends Statement{
		private final Statement left;
		private final Statement right;
		
		public StatementPair(Statement left, Statement right) {
			Objects.requireNonNull(left);
			Objects.requireNonNull(right);
			this.left = left;
			this.right = right;
		}
		public void execute(Context context) {
			this.left.execute(context);
			this.right.execute(context);
		}
	}
}
