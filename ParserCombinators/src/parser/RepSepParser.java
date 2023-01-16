package parser;

import scanner.Scanner;
import scanner.Symbol;

public class RepSepParser<T, S> extends Parser<T> {
    public static <T, S> RepSepParser<T, S> repSep(Parser<T> parser, Parser<S> sepParser, TriFunction<T, S, T, T> reducer) {
        return new RepSepParser<>(parser, sepParser, reducer);
    }

    private final Parser<T> parser;
    private final Parser<S> sepParser;
    private final TriFunction<T, S, T, T> reducer;

    private RepSepParser(Parser<T> parser, Parser<S> sepParser, TriFunction<T, S, T, T> reducer) {
        this.parser = parser;
        this.sepParser = sepParser;
        this.reducer = reducer;
    }

    @Override
    protected Parser<?>[] getParsers() {
        return new Parser<?>[]{this.parser, this.sepParser};
    }

    @Override
    protected boolean canParse(Symbol symbol) {
        return this.parser.canParse(symbol);
    }

    @Override
    protected T doParse(Scanner s) {
        T value = this.parser.doParse(s);
        while (this.sepParser.canParse(s.current())) {
            S sep = this.sepParser.doParse(s);
            T v = this.parser.parse(s);
            value = this.reducer.apply(value, sep, v);
        }
        return value;
    }
}
