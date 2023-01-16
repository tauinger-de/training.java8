package parser;

import java.util.HashSet;
import java.util.Set;

import scanner.Scanner;
import scanner.Specials;
import scanner.Symbol;

public class SpecialParser extends Parser<String> {

	public static SpecialParser $(String token) {
		return new SpecialParser(token);
	}
	
	public final String token;
	
	private SpecialParser(String token) {
		this.token = token;
	}

	@Override
	protected Parser<?>[] getParsers() {
		return new Parser<?>[] { };
	}

	@Override
	protected boolean canParse(Symbol symbol) {
		return symbol != null && symbol.getAsText().equals(this.token);
	}

	@Override
	protected String doParse(Scanner scanner) {
		scanner.next();
		return this.token;
	}
	
	public static Specials createSpecials(Parser<?> parser) {
		final Specials specials = new Specials();
		final Set<Parser<?>> visited = new HashSet<>();
		collectSpecials(visited, specials, parser);
		return specials;
	}
	
	private static void collectSpecials(Set<Parser<?>> visited, Specials specials, Parser<?> parser) {
		if (visited.contains(parser))
			return;
		visited.add(parser);
		if (parser instanceof SpecialParser) { 
			specials.create(((SpecialParser)parser).token);
		}
		else { 
			for (Parser<?> p : parser.getParsers()) {
				collectSpecials(visited, specials, p);
			}
		}
	}
}
