package parser;

import scanner.Scanner;
import scanner.Symbol;

public class StringParser extends Parser<String> {
    public static final StringParser string = new StringParser();

    private StringParser() {
    }

    @Override
    protected Parser<?>[] getParsers() {
        return new Parser<?>[]{};
    }

    @Override
    protected boolean canParse(Symbol symbol) {
        return symbol != null && symbol.isLiteral() && symbol.asLiteral().isString();
    }

    @Override
    protected String doParse(Scanner scanner) {
        final String result = scanner.current().asLiteral().stringValue();
        scanner.next();
        return result;
    }
}

