package tests;

import static parser.NumberParser.number;
import static parser.SpecialParser.$;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import parser.CustomParser;
import parser.Parser;
import parser.SpecialParser;
import scanner.Scanner;
import scanner.Specials;

public class CustomParserTest {

	static class EnclosedSumParser extends CustomParser<Integer> {
		@Override
		protected Parser<Integer> buildParser() {
			Parser<Integer> sum = number.left($("+")).and(number, (l, r) -> l + r);
			Parser<Integer> enclosedSum = $("(").right(sum).left($(")"));
			return enclosedSum;
		}
	}
	
	@Test
	public void testEnclosedSumParser1() {
		EnclosedSumParser parser = new EnclosedSumParser();
		Specials specials = new Specials();
		specials.create("+");
		specials.create(")");
		specials.create("(");
		Scanner scanner = new Scanner(specials, new StringReader(" (2 + 40) "));
		scanner.next();
		Assert.assertEquals(Integer.valueOf(42), parser.parse(scanner));
		Assert.assertNull(scanner.current());
	}
	
	@Test
	public void testEnclosedSumParser2() {
		EnclosedSumParser parser = new EnclosedSumParser();
		Specials specials = SpecialParser.createSpecials(parser);
		Scanner scanner = new Scanner(specials, new StringReader(" (2 + 40) "));
		scanner.next();
		Assert.assertEquals(Integer.valueOf(42), parser.parse(scanner));
		Assert.assertNull(scanner.current());
	}

	@Test
	public void testEnclosedSumParser3() {
		EnclosedSumParser parser = new EnclosedSumParser();
		Assert.assertEquals(Integer.valueOf(42), parser.parse(" (2 + 40)" ));
	}
}
