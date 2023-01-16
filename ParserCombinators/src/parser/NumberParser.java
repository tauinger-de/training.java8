package parser;

import scanner.Scanner;
import scanner.Symbol;

public class NumberParser extends Parser<Integer> {

    public static final NumberParser number = new NumberParser();

    private NumberParser() {
    }

    @Override
    protected Parser<?>[] getParsers() {
        return new Parser<?>[]{};
    }

    @Override
    protected boolean canParse(Symbol symbol) {
        return symbol != null && symbol.isLiteral() && symbol.asLiteral().isInt();
    }

    @Override
    protected Integer doParse(Scanner scanner) {
        final int result = scanner.current().asLiteral().intValue();
        scanner.next();
        return result;
    }
}

