package parser;

import scanner.Scanner;
import scanner.Symbol;

public class OptParser<R> extends Parser<R> {
    public static <R> OptParser<R> opt(Parser<R> parser) {
        return new OptParser<R>(parser);
    }

    private final Parser<R> parser;

    public OptParser(Parser<R> parser) {
        this.parser = parser;
    }

    @Override
    protected Parser<?>[] getParsers() {
        return new Parser<?>[]{this.parser};
    }

    @Override
    protected boolean canParse(Symbol symbol) {
        return true;
    }

    @Override
    protected R doParse(Scanner scanner) {
        final R result;
        if (this.parser.canParse(scanner.current()))
            result = this.parser.doParse(scanner);
        else
            result = null;
        return result;
    }
}
