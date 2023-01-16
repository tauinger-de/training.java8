package parser;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import scanner.Scanner;
import scanner.Symbol;

public abstract class Parser<T> {
	
	protected  abstract Parser<?>[] getParsers(); 
	
	protected abstract boolean canParse(Symbol symbol);
	protected abstract T doParse(Scanner scanner);
	public T parse(Scanner scanner) {
		if (! canParse(scanner.current()))
			throw new RuntimeException("cannot parse: " + this);
		return doParse(scanner);
	}
	
	public <R, OUT> AndParser<T, R, OUT> and(Parser<R> other, BiFunction<T, R, OUT> reducer) {
		Objects.requireNonNull(other);
		Objects.requireNonNull(reducer);
		return new AndParser<>(this, other, reducer);
	}
	
	public <R> AndParser<T, R, R> right(Parser<R> other) {
		Objects.requireNonNull(other);
		return new AndParser<>(this, other, (left, right) -> right);
	}

	public <R> AndParser<T, R, T> left(Parser<R> other) {
		Objects.requireNonNull(other);
		return new AndParser<>(this, other, (left, right) -> left);
	}

	public OrParser<T> or(Parser<T> other) {
		Objects.requireNonNull(other);
		return new OrParser<>(this, other);
	}
	
	public <R> MapParser<T,R> map(Function<T,R> mapper) {
		Objects.requireNonNull(mapper);
		return new MapParser<>(this, mapper);
	}

}
