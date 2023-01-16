package tests;

import org.junit.Assert;
import org.junit.Test;
import parser.CustomParser;
import parser.Parser;
import parser.util.Pair;

import java.util.function.BiFunction;

import static parser.NumberParser.number;
import static parser.SpecialParser.$;

public class MapParserTest {

    static class IntToStringParser extends CustomParser<String> {
        @Override
        protected Parser<String> buildParser() {
            return number.map(n -> String.valueOf(n));
        }
    }

    @Test
    public void testIntToStringParser() {
        IntToStringParser p = new IntToStringParser();
        Assert.assertEquals("1234", p.parse(" 1234 "));
    }

    static class AddParser1 extends CustomParser<String> {
        @Override
        protected Parser<String> buildParser() {
            return
                    number
                            .left($("+"))
                            .and(number, (n1, n2) -> String.valueOf(n1) + String.valueOf(n2));
        }
    }

    @Test
    public void testAndParser1() {
        AddParser1 p = new AddParser1();
        Assert.assertEquals("1234", p.parse("12 + 34"));
    }

    static class AddParser2 extends CustomParser<String> {
        @Override
        protected Parser<String> buildParser() {
            return
                    number.map(n -> String.valueOf(n))
                            .left($("+"))
                            .and(number.map(n -> String.valueOf(n)), (s1, s2) -> s1 + s2);
        }
    }

    @Test
    public void testAndParser2() {
        AddParser2 p = new AddParser2();
        Assert.assertEquals("1234", p.parse("12 + 34"));
    }

    static class ComparisonParser1 extends CustomParser<Boolean> {
        @Override
        protected Parser<Boolean> buildParser() {
            final Parser<String> compareOp = $("==").or($(">")).or($("<"));
            return number
                    .and(compareOp,
                            (num, op) -> new Pair<Integer, String>(num, op))
                    .and(number,
                            (Pair<Integer, String> pair, Integer num) -> {
                                final int n1 = pair.left;
                                final String op = pair.right;
                                final int n2 = num;
                                if (op.equals("<"))
                                    return n1 < n2;
                                if (op.equals(">"))
                                    return n1 > n2;
                                return n1 == n2;
                            });
        }
    }

    @Test
    public void testComparisonParser1() {
        ComparisonParser1 p = new ComparisonParser1();
        Assert.assertTrue(p.parse("1 == 1"));
        Assert.assertTrue(p.parse("2 > 1"));
        Assert.assertTrue(p.parse("1 < 2"));
    }


    static interface IntComp extends BiFunction<Integer, Integer, Boolean> {
    }

    ;

    static class ComparisonParser2 extends CustomParser<Boolean> {
        @Override
        protected Parser<Boolean> buildParser() {
            final Parser<IntComp> compareOp =
                    $("==").map(s -> (IntComp) (x, y) -> x == y)
                            .or($(">").map(s -> (IntComp) (x, y) -> x > y))
                            .or($("<").map(s -> (IntComp) (x, y) -> x < y));

            return number
                    .and(compareOp,
                            (num, op) -> new Pair<Integer, IntComp>(num, op))
                    .and(number,
                            (Pair<Integer, IntComp> pair, Integer num)
                                    -> pair.right.apply(pair.left, num));
        }
    }

    @Test
    public void testComparisonParser2() {
        ComparisonParser2 p = new ComparisonParser2();
        Assert.assertTrue(p.parse("1 == 1"));
        Assert.assertTrue(p.parse("2 > 1"));
        Assert.assertTrue(p.parse("1 < 2"));
    }
}
