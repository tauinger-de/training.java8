package example2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parser.util.Value;

public class FullExpressionParserTest {

	private FullExpressionParser parser;
	
	@Before
	public void setUp() {
		this.parser = new FullExpressionParser();
	}
	
	@Test
	public void testNumeric() {
		Assert.assertEquals(Value.of(-2 * (3 + (4)) / 12 - -2), 
				this.parser.parse("-2 * (3 + (4)) / 12 - -2"));
	}
	
	@Test
	public void testComparison() {
		Assert.assertEquals(Value.of(-2 * (3 + (4)) / 12 >= -2), 
				this.parser.parse("-2 * (3 + (4)) / 12 >= -2"));
	}

	@Test
	public void testLogical() {
		Assert.assertEquals(Value.of(-2 * (3 + (4)) / 12 >= -2 && 3 > 1), 
				this.parser.parse("-2 * (3 + (4)) / 12 >= -2  && 3 > 1"));
	}
}
