package example1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleExpressionParserTest {

	private SimpleExpressionParser parser;
	
	@Before 
	public void setUp() {
		 this.parser = new SimpleExpressionParser();
		
	}
	@Test
	public void testSimpleNumber() {
		Assert.assertEquals(Integer.valueOf(42), 
			this.parser.parse("42"));
	}

	@Test
	public void testMultiplicative() {
		Assert.assertEquals(Integer.valueOf(2 * 3 * 4 / 12 * 2), 
				this.parser.parse("2 * 3 * 4 / 12 * 2"));
	}

	@Test
	public void testMultiplicativeAndAdditive() {
		Assert.assertEquals(Integer.valueOf(2 * 3 + 4 / 12 - 2), 
				this.parser.parse("2 * 3 + 4 / 12 - 2"));
	}

	@Test
	public void testUnary() {
		Assert.assertEquals(Integer.valueOf(-2 * 3 + 4 / 12 - -2), 
				this.parser.parse("-2 * 3 + 4 / 12 - -2"));
	}

	@Test
	public void testNested() {
		Assert.assertEquals(Integer.valueOf(-2 * (3 + (4)) / 12 - -2), 
				this.parser.parse("-2 * (3 + (4)) / 12 - -2"));
	}
}
