package example3;

import parser.CustomParser;
import parser.Parser;
import parser.util.Ops;
import parser.util.Ops.Binary;
import parser.util.Ops.Unary;
import parser.util.Pair;

import static parser.ForwardParser.forward;
import static parser.IdentParser.ident;
import static parser.NumberParser.number;
import static parser.OptParser.opt;
import static parser.RepSepParser.repSep;
import static parser.SpecialParser.$;
import static parser.StringParser.string;

public class ExpressionParser extends CustomParser<Expression> {

    private Parser<Expression> or;

    @Override
    protected Parser<Expression> buildParser() {

        final Parser<Expression> expression =
                forward(() -> or);

        final Parser<Expression> atom =
                number.map(num -> Expression.of(num))
                        .or(string.map(str -> Expression.of(str)))
                        .or(ident.map(id -> Expression.variable(id)))
                        .or($("true").map(s -> Expression.of(true)))
                        .or($("false").map(s -> Expression.of(false)));

        final Parser<Expression> nested =
                $("(").right(expression).left($(")"));

        final Parser<Unary> unaryOp =
                $("-").map(s -> Ops.negative).or($("!").map(s -> Ops.not));

        final Parser<Expression> simple =
                opt(unaryOp).and(atom.or(nested), (unary, value)
                        -> unary == null ? value : Expression.unary(unary, value));

        final Parser<Expression> multiplicative =
                repSep(simple, $("*").or($("/")),
                        (v1, op, v2) -> Expression.binary(
                                op.equals("*") ? Ops.times : Ops.div, v1, v2));

        final Parser<Expression> additive =
                repSep(multiplicative, $("+").or($("-")),
                        (v1, op, v2) -> Expression.binary(
                                op.equals("+") ? Ops.plus : Ops.minus, v1, v2));

        final Parser<Binary> comparisonOp =
                $("==").map(s -> Ops.eq)
                        .or($("!=").map(s -> Ops.ne))
                        .or($(">").map(s -> Ops.gt))
                        .or($("<").map(s -> Ops.lt))
                        .or($(">=").map(s -> Ops.ge))
                        .or($("<=").map(s -> Ops.le));

        final Parser<Expression> comparison =
                additive.and(
                        opt(comparisonOp.and(additive,
                                (op, v) -> new Pair<Binary, Expression>(op, v))),
                        (Expression left, Pair<Binary, Expression> pair) ->
                                pair == null ? left :
                                        Expression.binary(pair.left, left, pair.right));

        final Parser<Expression> and =
                repSep(comparison, $("&&"), (v1, s, v2) -> Expression.binary(Ops.and, v1, v2));

        this.or =
                repSep(and, $("||"), (v1, s, v2) -> Expression.binary(Ops.or, v1, v2));

        return expression;
    }
}
