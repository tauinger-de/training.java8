package parser;

import scanner.Scanner;
import scanner.Symbol;

public class OrParser<T> extends Parser<T> {

	private final Parser<T> left;
	private final Parser<T> right;

	OrParser(Parser<T> left, Parser<T> right) {
		this.left = left;
		this.right = right;
	}

	@Override
	protected Parser<?>[] getParsers() {
		return new Parser<?>[] { this.left, this.right };
	}

	
	@Override
	protected boolean canParse(Symbol symbol) {
		return this.left.canParse(symbol) || this.right.canParse(symbol);
	}

	@Override
	protected T doParse(Scanner scanner) {
		T result;
		if (this.left.canParse(scanner.current()))
			result = this.left.doParse(scanner);
		else
			result = this.right.doParse(scanner);
		return result;
	}

}
