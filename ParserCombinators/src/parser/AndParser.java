package parser;

import scanner.Scanner;
import scanner.Symbol;

import java.util.function.BiFunction;

public class AndParser<L, R, T> extends Parser<T> {

    private final Parser<L> left;
    private final Parser<R> right;
    private final BiFunction<L, R, T> reducer;

    AndParser(Parser<L> left, Parser<R> right, BiFunction<L, R, T> reducer) {
        this.left = left;
        this.right = right;
        this.reducer = reducer;
    }

    @Override
    protected Parser<?>[] getParsers() {
        return new Parser<?>[]{this.left, this.right};
    }

    @Override
    protected boolean canParse(Symbol symbol) {
        return this.left.canParse(symbol);
    }

    @Override
    protected T doParse(Scanner scanner) {
        final L l = this.left.doParse(scanner);
        final R r = this.right.parse(scanner);
        return this.reducer.apply(l, r);
    }

}
