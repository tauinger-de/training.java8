package example3;

import static parser.ForwardParser.forward;
import static parser.IdentParser.ident;
import static parser.OptParser.opt;
import static parser.RepSepParser.repSep;
import static parser.SpecialParser.$;
import parser.CustomParser;
import parser.Parser;
import parser.util.Pair;

public class StatementParser extends CustomParser<Statement> {

	private Parser<Statement> stmt;

	@Override
	protected Parser<Statement> buildParser() {
		
		final ExpressionParser expression = new ExpressionParser();
		
		final Parser<Statement> statement =
				forward(() -> this.stmt);
		
		final Parser<Statement> list = 
				repSep(statement, $(";"), 
						(stmt1, semi, stmt2) -> Statement.createPair(stmt1, stmt2));
		
		final Parser<Statement> block =
				$("{").right(list).left($("}"));
		
		final Parser<Expression> enclosedCondition = 
				$("(").right(expression).left($(")"));
		
		final Parser<Statement> print =
				$("print").right(enclosedCondition)
						.map(expr -> Statement.createPrint(expr));
		
		final Parser<Statement> ifStatement = 
				$("if").right(enclosedCondition)
				.and(statement, (expr, stmt) 
						-> new Pair<Expression, Statement>(expr, stmt))
				.and(opt($("else").right(statement)), (pair, stmt) 
						-> Statement.createIf(pair.left, pair.right, stmt));

		final Parser<Statement> whileStatement =
				$("while").right(enclosedCondition).and(statement, (expr, stmt) 
						-> Statement.createWhile(expr, stmt));

		final Parser<Statement> assignment =
				ident.left($("=")).and(expression, 
						(id, expr) -> Statement.createAssignment(id, expr));  
				
		this.stmt = block.or(print).or(ifStatement).or(whileStatement).or(assignment);
		
		return statement;
	}
}

