package tests;

import static parser.NumberParser.number;
import static parser.StringParser.string;
import static parser.SpecialParser.$;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import parser.AndParser;
import parser.Parser;
import scanner.Scanner;
import scanner.Specials;

public class AndParserTest {
	
	@Test
	public void testAndWithInputsAndOutputOfSameType() { 
		final Scanner scanner = new Scanner(null, new StringReader(" 40 2 "));
		scanner.next();
		AndParser<Integer, Integer, Integer> p = 
			number.and(number, (n1, n2) -> n1 + n2);
		Assert.assertEquals(Integer.valueOf(42), p.parse(scanner));
	}

	@Test
	public void testAndWithInputsOfSameType() { 
		final Scanner scanner = new Scanner(null, new StringReader(" 40 2 "));
		scanner.next();
		AndParser<Integer, Integer, String> p = 
			number.and(number, (n1, n2) -> String.valueOf(n1) + String.valueOf(n2));
		Assert.assertEquals("402", p.parse(scanner));
	}

	@Test
	public void testAndWithInputsOfDifferentType() { 
		final Scanner scanner = new Scanner(null, new StringReader(" 123 \"123\" "));
		scanner.next();
		AndParser<Integer, String, Boolean> p = 
			number.and(string, (n, s) -> n.toString().equals(s));
		Assert.assertTrue(p.parse(scanner));
	}

	@Test
	public void testRight() { 
		final Scanner scanner = new Scanner(null, new StringReader(" 123 \"uvw\" "));
		scanner.next();
		Parser<String> p = number.right(string);
		Assert.assertEquals("uvw", p.parse(scanner));
	}

	@Test
	public void testLeft() { 
		final Scanner scanner = new Scanner(null, new StringReader(" 123 \"uvw\" "));
		scanner.next();
		Parser<Integer> p = number.left(string);
		Assert.assertEquals(Integer.valueOf(123), p.parse(scanner));
	}

	@Test
	public void testLeftAndRight() { 
		final Scanner scanner = new Scanner(null, new StringReader(" 123 \"uvw\" 456"));
		scanner.next();
		Parser<String> p = number.right(string).left(number);
		Assert.assertEquals("uvw", p.parse(scanner));
	}

	@Test
	public void testThreeLeftsWithOndAnd() { 
		final Scanner scanner = new Scanner(null, new StringReader(" 42 123 \"uvw\" 456 58"));
		scanner.next();
		Parser<Integer> p = number.left(number).left(string).left(number).and(number, (x, y) -> x + y);
		Assert.assertEquals(Integer.valueOf(42 + 58), p.parse(scanner));
	}

	@Test
	public void testThreeRightsWithOndAnd() { 
		final Scanner scanner = new Scanner(null, new StringReader(" 42 123 \"uvw\" 456 58"));
		scanner.next();
		Parser<Integer> p = number.right(number).right(string).right(number).and(number, (x, y) -> x + y);
		Assert.assertEquals(Integer.valueOf(456 + 58), p.parse(scanner));
	}

	@Test
	public void testTwoAndParsers1() { 
		Specials specials = new Specials();
		specials.create("+");
		final Scanner scanner = new Scanner(specials, new StringReader(" 40 + 2"));
		scanner.next();
		Parser<Integer> p = number.and($("+"), (l, r) -> l).and(number, (l, r) -> l + r);
		Assert.assertEquals(Integer.valueOf(42), p.parse(scanner));
	}

	@Test
	public void testTwoAndParsers2() { 
		Specials specials = new Specials();
		specials.create("+");
		final Scanner scanner = new Scanner(specials, new StringReader(" + 40 2"));
		scanner.next();
		Parser<Integer> p = $("+").and(number, (l, r) -> r).and(number, (l, r) -> l + r);
		Assert.assertEquals(Integer.valueOf(42), p.parse(scanner));
	}

	@Test
	public void testLeftAnd() { 
		Specials specials = new Specials();
		specials.create("+");
		final Scanner scanner = new Scanner(specials, new StringReader(" 40 + 2"));
		scanner.next();
		Parser<Integer> p = number.left($("+")).and(number, (l, r) -> l + r);
		Assert.assertEquals(Integer.valueOf(42), p.parse(scanner));
	}

	@Test
	public void testRightAnd() { 
		Specials specials = new Specials();
		specials.create("+");
		final Scanner scanner = new Scanner(specials, new StringReader(" + 40 2"));
		scanner.next();
		Parser<Integer> p = $("+").right(number).and(number, (l, r) -> l + r);
		Assert.assertEquals(Integer.valueOf(42), p.parse(scanner));
	}
}
