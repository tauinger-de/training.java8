package example2;

import static parser.ForwardParser.forward;
import static parser.NumberParser.number;
import static parser.OptParser.opt;
import static parser.RepSepParser.repSep;
import static parser.SpecialParser.$;
import parser.CustomParser;
import parser.Parser;
import parser.util.Ops.Binary;
import parser.util.Ops.Unary;
import parser.util.Pair;
import parser.util.Value;

public class FullExpressionParser extends CustomParser<Value> {

	private Parser<Value> or;

	protected Parser<Value> buildParser() {
		
		final Parser<Value> expression =
				forward(() -> this.or);
		
		final Parser<Value> num =
				number.map(x -> Value.of(x));
		
		final Parser<Value> nested = 
				$("(").right(expression).left($(")"));

		final Parser<Unary> unaryOp = 
				$("-").map(s -> (Unary) v -> v.negative())
				.or($("!").map(s -> (Unary) v -> v.not()));

		final Parser<Value> simple =
				opt(unaryOp).and(num.or(nested), 
						(unary, value) -> unary == null ? value : unary.apply(value));
		
		final Parser<Value> multiplicative = 
				repSep(simple, $("*").or($("/")), 
						(v1, op, v2) -> op.equals("*") ? v1.times(v2) : v1.div(v2));
		
		final Parser<Value> additive =
				repSep(multiplicative, $("+").or($("-")), 
						(v1, op, v2) -> op.equals("+") ? v1.plus(v2) : v1.minus(v2));
		
		final Parser<Binary> comparisonOp =
				$("==").map(s -> (Binary) (v1, v2) -> v1.eq(v2))
				.or($(">").map(s -> (Binary) (v1, v2) -> v1.gt(v2)))
				.or($("<").map(s -> (Binary) (v1, v2) -> v1.lt(v2)))
				.or($(">=").map(s -> (Binary) (v1, v2) -> v1.ge(v2)))
				.or($("<=").map(s -> (Binary) (v1, v2) -> v1.le(v2)));
		
		final Parser<Value> comparison = 
				additive.and(opt(comparisonOp.and(additive, 
						(op, v) -> new Pair<Binary, Value>(op,v))), 
				(Value left, Pair<Binary, Value> pair) -> 
						pair == null ? left : pair.left.apply(left, pair.right)); 
		
		final Parser<Value> and = 
				repSep(comparison, $("&&"), (v1, s, v2) -> v1.and(v2));
		
		this.or = repSep(and, $("||"), (v1, s, v2) -> v1.or(v2));

		return expression;
	}
}
