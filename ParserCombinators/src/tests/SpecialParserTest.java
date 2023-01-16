package tests;

import static parser.SpecialParser.$;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import scanner.Scanner;
import scanner.Specials;


public class SpecialParserTest {
	
	@Test
	public void testSpecialParsers() { 
		final Specials specials = new Specials();
		specials.create("and");
		specials.create("while");
		specials.create(">=");
		specials.create("*");
		specials.create("-");
		final Scanner scanner = new Scanner(specials, new StringReader(" * - and while >= "));
		scanner.next();
		Assert.assertEquals("*", $("*").parse(scanner));
		Assert.assertEquals("-", $("-").parse(scanner));
		Assert.assertEquals("and", $("and").parse(scanner));
		Assert.assertEquals("while", $("while").parse(scanner));
		Assert.assertEquals(">=", $(">=").parse(scanner));
		Assert.assertNull(scanner.current());
	}
}
