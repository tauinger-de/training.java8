package example1;

import parser.CustomParser;
import parser.Parser;

import java.util.function.UnaryOperator;

import static parser.ForwardParser.forward;
import static parser.NumberParser.number;
import static parser.OptParser.opt;
import static parser.RepSepParser.repSep;
import static parser.SpecialParser.$;

public class SimpleExpressionParser extends CustomParser<Integer> {

    private Parser<Integer> additive;

    protected Parser<Integer> buildParser() {

        final Parser<Integer> expression =
                forward(() -> this.additive);

        final Parser<Integer> nested =
                $("(").right(expression).left($(")"));

        final Parser<UnaryOperator<Integer>> unaryOp =
                $("-").map(s -> (UnaryOperator<Integer>) v -> -v);

        final Parser<Integer> simple =
                opt(unaryOp).and(number.or(nested),
                        (unary, value) -> unary == null ? value : unary.apply(value));

        final Parser<Integer> multiplicative =
                repSep(simple, $("*").or($("/")),
                        (v1, op, v2) -> op.equals("*") ? v1 * v2 : v1 / v2);

        this.additive =
                repSep(multiplicative, $("+").or($("-")),
                        (v1, op, v2) -> op.equals("+") ? v1 + v2 : v1 - v2);

        return expression;
    }
}
