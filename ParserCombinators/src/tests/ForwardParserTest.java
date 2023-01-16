package tests;

import static parser.ForwardParser.forward;
import static parser.NumberParser.number;
import static parser.OptParser.opt;
import static parser.SpecialParser.$;

import org.junit.Assert;
import org.junit.Test;

import parser.CustomParser;
import parser.Parser;

public class ForwardParserTest {

//	static class ExprParser1 extends CustomParser<Integer> {
//		@Override
//		protected Parser<Integer> buildParser() {
//			Parser<Integer> expr = number
//					.or($("(").right(expr).left($(")")));  // doesn't work
//			return expr;
//		}
//	}
	
	static class ExprParser2 extends CustomParser<Integer> {
		private Parser<Integer> ex;
		@Override
		protected Parser<Integer> buildParser() {
			Parser<Integer> expr = forward(() -> this.ex);
			this.ex = number
					.or($("(").right(expr).left($(")")));
			return this.ex;
		}
	}

	@Test
	public void testExprParser() { 
		ExprParser2 p = new ExprParser2();
		Assert.assertEquals(Integer.valueOf(42), p.parse("42"));
		Assert.assertEquals(Integer.valueOf(42), p.parse("(((42)))"));
	}
	
	
	static class SumParser extends CustomParser<Integer> {
		private Parser<Integer> ex;
		@Override
		protected Parser<Integer> buildParser() {
			Parser<Integer> expr = forward(() -> this.ex);
			this.ex = number.and(
					opt($("+").right(expr)), (x, opt) -> opt == null ? x : x + opt);
			return this.ex;
		}
	}
	
	@Test
	public void testSumParser() { 
		SumParser p = new SumParser();
		Assert.assertEquals(Integer.valueOf(42), p.parse("42"));
		Assert.assertEquals(Integer.valueOf(42), p.parse("40 + 2"));
		Assert.assertEquals(Integer.valueOf(42), p.parse("40 + 1 + 1"));
	}

}
