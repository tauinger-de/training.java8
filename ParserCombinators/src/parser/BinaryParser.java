package parser;


public abstract class BinaryParser<L, R, T> extends Parser<T> {

    final Parser<L> left;
    final Parser<R> right;

    BinaryParser(Parser<L> left, Parser<R> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    protected Parser<?>[] getParsers() {
        return new Parser<?>[]{this.left, this.right};
    }
}
