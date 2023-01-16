package tests;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import static parser.IdentParser.ident;
import static parser.NumberParser.number;
import static parser.StringParser.string;
import scanner.Scanner;

public class NumberStringIdentParserTest {

	@Test
	public void testNumberParser() {
		final Scanner scanner = new Scanner(null, new StringReader(" 42 77"));
		scanner.next();
		Assert.assertEquals(Integer.valueOf(42), number.parse(scanner));
		Assert.assertEquals(Integer.valueOf(77), number.parse(scanner));
		Assert.assertNull(scanner.current());
	}

	@Test
	public void testStringParser() {
		final Scanner scanner = new Scanner(null, new StringReader(" \"hello\" \"world\" "));
		scanner.next();
		Assert.assertEquals("hello", string.parse(scanner));
		Assert.assertEquals("world", string.parse(scanner));
		Assert.assertNull(scanner.current());
	}

	@Test
	public void testIdentParser() {
		final Scanner scanner = new Scanner(null, new StringReader(" hello world "));
		scanner.next();
		Assert.assertEquals("hello", ident.parse(scanner));
		Assert.assertEquals("world", ident.parse(scanner));
		Assert.assertNull(scanner.current());
	}

	@Test
	public void testAlltogether() {
		final Scanner scanner = new Scanner(null, new StringReader(" 42 \"hello\" world "));
		scanner.next();
		Assert.assertEquals(Integer.valueOf(42), number.parse(scanner));
		Assert.assertEquals("hello", string.parse(scanner));
		Assert.assertEquals("world", ident.parse(scanner));
		Assert.assertNull(scanner.current());
	}
}
