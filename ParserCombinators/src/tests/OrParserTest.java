package tests;

import static parser.NumberParser.number;
import static parser.SpecialParser.$;

import org.junit.Assert;
import org.junit.Test;

import parser.CustomParser;
import parser.Parser;

public class OrParserTest {

	static class PlusOrMinusAndNumberParser extends CustomParser<Integer> {
		@Override
		protected Parser<Integer> buildParser() {
			return $("+").or($("-")).and(number, (op, num) -> op.equals("+") ? num : -num);
		}
	}
	
	@Test
	public void testPlusOrMinusAndNumberParser() { 
		PlusOrMinusAndNumberParser p = new PlusOrMinusAndNumberParser();
		Assert.assertEquals(Integer.valueOf(42), p.parse("+ 42"));
		Assert.assertEquals(Integer.valueOf(-42), p.parse("-42"));
	}

	static class AdditiveParser extends CustomParser<Integer> {
		@Override
		protected Parser<Integer> buildParser() {
			return number.and(new PlusOrMinusAndNumberParser(), (num1, num2) -> num1 + num2);
		}
	}
	
	@Test
	public void testAdditiveParser() { 
		AdditiveParser p = new AdditiveParser();
		Assert.assertEquals(Integer.valueOf(42), p.parse("1 + 41"));
		Assert.assertEquals(Integer.valueOf(42), p.parse("43-1"));
	}
}
