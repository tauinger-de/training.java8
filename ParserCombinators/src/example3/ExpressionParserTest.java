package example3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parser.util.Value;

public class ExpressionParserTest {

	private ExpressionParser parser;

	@Before
	public void setUp() {
		parser = new ExpressionParser();
	}

	@Test
	public void testNumeric() {
		String s = "(2 * -(3 + 4 *(3 +2 )) * -5 + ((7)))";
		Assert.assertEquals(Value.of(2 * -(3 + 4 * (3 + 2)) * -5 + ((7))), 
			this.parser.parse(s).evaluate(new Context()));
	}

	@Test
	public void testComparison() {
		String s = "(2 * -(3 + 4 *(3 +2 )) * -5 + ((7)) >= 2)";
		Assert.assertEquals(Value.of((2 * -(3 + 4 *(3 +2 )) * -5 + ((7)) >= 2)),
			this.parser.parse(s).evaluate(new Context()));
	}

	@Test
	public void testLogical() {
		String s = "(2 * -(3 + 4 *(3 +2 )) * -5 + ((7)) >= 2 && 3 > 1)";
		Assert.assertEquals(Value.of((2 * -(3 + 4 *(3 +2 )) * -5 + ((7)) >= 2  && 3 > 1)),
			this.parser.parse(s).evaluate(new Context()));
	}
}
