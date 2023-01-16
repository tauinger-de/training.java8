package parser;

import java.io.StringReader;

import scanner.Scanner;
import scanner.Specials;
import scanner.Symbol;

public abstract class CustomParser<T> extends Parser<T> {

	private Parser<T> parser;
	
	private Parser<T> getParser() {
		if (this.parser == null)
			this.parser = this.buildParser();
		return this.parser;
	}
	protected abstract Parser<T> buildParser();
	
	@Override
	protected  Parser<?>[] getParsers() {
		return new Parser<?>[] { this.getParser() };
	}

	@Override
	protected boolean canParse(Symbol symbol) {
		return this.getParser().canParse(symbol);
	}

	@Override
	protected T doParse(Scanner scanner) {
		return this.getParser().doParse(scanner);
	}

	public T parse(String source) {
		final Specials specials = SpecialParser.createSpecials(this);
		final Scanner scanner = new Scanner(specials, new StringReader(source));
		scanner.next();
		final T result = this.parse(scanner);
		if (scanner.current() != null)
			throw new RuntimeException("eof expected");
		return result;
	}
}
