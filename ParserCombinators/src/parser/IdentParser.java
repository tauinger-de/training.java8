package parser;

import scanner.Scanner;
import scanner.Symbol;

public class IdentParser extends Parser<String> {
    public static final IdentParser ident = new IdentParser();

    private IdentParser() {
    }

    @Override
    protected Parser<?>[] getParsers() {
        return new Parser<?>[]{};
    }

    @Override
    protected boolean canParse(Symbol symbol) {
        return symbol != null && symbol.isIdentifier();
    }

    @Override
    protected String doParse(Scanner scanner) {
        final String result = scanner.current().asIdentifier().getValue();
        scanner.next();
        return result;
    }
}

