package tests;

import static parser.NumberParser.number;
import static parser.SpecialParser.$;
import static parser.OptParser.opt;

import org.junit.Assert;
import org.junit.Test;

import parser.CustomParser;
import parser.Parser;

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
