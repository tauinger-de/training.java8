package tests;

import org.junit.Assert;
import org.junit.Test;
import parser.CustomParser;
import parser.Parser;

import static parser.NumberParser.number;
import static parser.OptParser.opt;
import static parser.SpecialParser.$;

public class OptParserTest {

    static class OptMinusNumberParser extends CustomParser<Integer> {
        @Override
        protected Parser<Integer> buildParser() {
            return opt($("-")).and(number, (minus, num) -> minus == null ? num : -num);
        }
    }

    @Test
    public void testOptMinusNumberParser() {
        OptMinusNumberParser p = new OptMinusNumberParser();
        Assert.assertEquals(Integer.valueOf(42), p.parse("42"));
        Assert.assertEquals(Integer.valueOf(-42), p.parse("-42"));
    }

}
