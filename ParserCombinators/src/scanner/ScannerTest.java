package scanner;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import scanner.Scanner;
import scanner.Special;
import scanner.Specials;
import scanner.Symbol;

public class ScannerTest {
	@Test
	public void testSimple() {
		final String s =" 42 3.14 \"Hello\" 'A' abc";
		Scanner scanner = new Scanner(null, new StringReader(s));
		Symbol symbol;

		scanner.next();
		symbol = scanner.current();
		Assert.assertTrue(symbol.isLiteral());
		Assert.assertTrue(symbol.asLiteral().isInt());
		Assert.assertEquals(42, symbol.asLiteral().intValue());

		scanner.next();
		symbol = scanner.current();
		Assert.assertTrue(symbol.isLiteral());
		Assert.assertTrue(symbol.asLiteral().isDouble());
		Assert.assertEquals(3.14, symbol.asLiteral().doubleValue(), 0.0);

		scanner.next();
		symbol = scanner.current();
		Assert.assertTrue(symbol.isLiteral());
		Assert.assertTrue(symbol.asLiteral().isString());
		Assert.assertEquals("Hello", symbol.asLiteral().stringValue());

		scanner.next();
		symbol = scanner.current();
		Assert.assertTrue(symbol.isLiteral());
		Assert.assertTrue(symbol.asLiteral().isChar());
		Assert.assertEquals('A', symbol.asLiteral().charValue());

		scanner.next();
		symbol = scanner.current();
		Assert.assertTrue(symbol.isIdentifier());
		Assert.assertEquals("abc", symbol.asIdentifier().getValue());

		scanner.next();
		symbol = scanner.current();
		Assert.assertNull(symbol);
	}
	
	@Test
	public void testSpecials() {
		final Specials specials = new Specials();
		final Special Plus = specials.create("+");
		final Special PlusPlus = specials.create("++");
		final Special MinusMinus = specials.create("--");
		final Special And = specials.create("and");

	
		final String s =" and+ ++ ----  or ";
		Scanner scanner = new Scanner(specials, new StringReader(s));

		scanner.next();
		Assert.assertSame(And, scanner.current());

		scanner.next();
		Symbol symbol = scanner.current();
		Assert.assertSame(Plus, symbol);

		scanner.next();
		Assert.assertEquals(PlusPlus, scanner.current());

		scanner.next();
		Assert.assertEquals(MinusMinus, scanner.current());

		scanner.next();
		Assert.assertEquals(MinusMinus, scanner.current());

		scanner.next();
		Assert.assertEquals("or", scanner.current().asIdentifier().getValue());

		scanner.next();
		Assert.assertNull(scanner.current());
	}
}
