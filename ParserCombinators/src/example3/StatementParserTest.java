package example3;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StatementParserTest {
	
	private final String LS = System.getProperty("line.separator");

	private StatementParser parser;
	private ByteArrayOutputStream out;
	private PrintStream ps;

	@Before
	public void setUp() {
		this.parser = new StatementParser();
		this.out = new ByteArrayOutputStream();
		this.ps = new PrintStream(out);
	}

	@Test
	public void testCountToFive() {
		String s = "";
		s += "{                     ";
		s += "    a = 0;            ";
		s += "    while (a < 5) {	";
		s += "         print(a);	";
		s += "         a = a + 1	";
		s += "    }					";
		s += "}						";
		
		this.parser.parse(s).execute(new Context(this.ps));

		String output = new String(this.out.toByteArray());
		Assert.assertEquals("0" + LS + "1" + LS + "2" + LS + "3" + LS + "4" + LS, output);
	}
	
	@Test
	public void testGGT() {
		String s = "";
		s += "{ 						";
		s += "    a = 55;				";
		s += "    b = 33;				";
		s += "    x = a;				";
		s += "    y = b;				";
		s += "    while(x != y) {		";
		s += "	      if(x > y)			";
		s += "            x = x - y		";
		s += "        else				";
		s += "            y = y - x		";
		s += "    };					";
		s += "    print(\"Der GGT von \" + a + \" und \" + b + \" ist \" + x)		";
		s += "}                         ";
		
		this.parser.parse(s).execute(new Context(this.ps));

		String output = new String(this.out.toByteArray());
		Assert.assertEquals("Der GGT von 55 und 33 ist 11" + LS, output);
	}
}
