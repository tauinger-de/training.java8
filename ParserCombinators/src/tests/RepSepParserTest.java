package tests;

import org.junit.Assert;
import org.junit.Test;
import parser.CustomParser;
import parser.Parser;
import parser.util.Node;

import java.util.List;

import static parser.IdentParser.ident;
import static parser.NumberParser.number;
import static parser.RepSepParser.repSep;
import static parser.SpecialParser.$;
import static parser.StringParser.string;

public class RepSepParserTest {

    static class SumParser extends CustomParser<Integer> {
        @Override
        protected Parser<Integer> buildParser() {
            return repSep(number, $("+"), (n1, op, n2) -> n1 + n2);
        }
    }

    @Test
    public void testSumParser() {
        SumParser p = new SumParser();
        Assert.assertEquals(Integer.valueOf(60), p.parse(" 10 + 20 + 30 "));
    }

    static class AdditiveParser extends CustomParser<Integer> {
        @Override
        protected Parser<Integer> buildParser() {
            return repSep(number, $("+").or($("-")), (n1, op, n2) -> op.equals("+") ? n1 + n2 : n1 - n2);
        }
    }

    @Test
    public void testAdditiveParser() {
        AdditiveParser p = new AdditiveParser();
        Assert.assertEquals(Integer.valueOf(20), p.parse(" 10 - 20 + 30 "));
    }

    static class StringConcatParser extends CustomParser<String> {
        @Override
        protected Parser<String> buildParser() {
            return repSep(string, $(","), (left, op, right) -> left + right);
        }
    }

    @Test
    public void testStringConcatParser() {
        StringConcatParser p = new StringConcatParser();
        Assert.assertEquals("alphabetagamma", p.parse(" \"alpha\"  ,  \"beta\",  \"gamma\""));
    }

    static class IdentListParser extends CustomParser<List<String>> {
        @Override
        protected Parser<List<String>> buildParser() {
            Parser<Node<String>> p = ident.map(id -> Node.of(id));
            return repSep(
                    p, $(","), (left, op, right) -> Node.of(left, right)).map(n -> n.list());
        }
    }

    @Test
    public void testIdentListParser() {
        IdentListParser p = new IdentListParser();
        List<String> list = p.parse(" alpha  ,  beta,  gamma");
        Assert.assertEquals(3, list.size());
        Assert.assertEquals("alpha", list.get(0));
        Assert.assertEquals("beta", list.get(1));
        Assert.assertEquals("gamma", list.get(2));
    }
}
