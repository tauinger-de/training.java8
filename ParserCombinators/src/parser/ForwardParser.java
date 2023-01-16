package parser;

import scanner.Scanner;
import scanner.Symbol;

import java.util.function.Supplier;

public class ForwardParser<R> extends Parser<R> {

    public static <R> ForwardParser<R> forward(Supplier<Parser<R>> supplier) {
        return new ForwardParser<>(supplier);
    }

    private Supplier<Parser<R>> supplier;
    private Parser<R> parser;

    private ForwardParser(Supplier<Parser<R>> supplier) {
        this.supplier = supplier;
    }

    @Override
    protected Parser<?>[] getParsers() {
        return new Parser<?>[]{this.get()};
    }

    private Parser<R> get() {
        if (this.parser == null) {
            this.parser = this.supplier.get();
            this.supplier = null;
        }
        return this.parser;
    }

    @Override
    protected boolean canParse(Symbol symbol) {
        return this.get().canParse(symbol);
    }

    @Override
    protected R doParse(Scanner scanner) {
        return this.get().doParse(scanner);
    }
}
