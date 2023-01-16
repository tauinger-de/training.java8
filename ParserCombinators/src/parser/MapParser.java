package parser;

import java.util.function.Function;

import scanner.Scanner;
import scanner.Symbol;

public class MapParser<T, R> extends Parser<R> {
	
	private final Parser<T> parser;
	private final Function<T, R> mapper;
	MapParser(Parser<T> parser, Function<T, R> mapper) {
		this.parser = parser;
		this.mapper= mapper;
	}

	@Override
	protected Parser<?>[] getParsers() {
		return new Parser<?>[] { this.parser };
	}
	
	@Override
	protected boolean canParse(Symbol symbol) {
		return parser.canParse(symbol);
	}

	@Override
	protected R doParse(Scanner s) {
		return mapper.apply(this.parser.doParse(s));
	}
}
