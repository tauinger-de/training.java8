package utils;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Stream<T> {
	
	public static <T> Stream<T> create(Iterable<T> iterable) {
		return new HeadNode<T>(iterable);
	}
	
    public abstract Stream<T> filter(Predicate<? super T> predicate);
    public abstract <R> Stream<R> map(Function<? super T, ? extends R> mapper);
    public abstract Stream<T> peek(Consumer<? super T> action);

    public abstract void forEach(Consumer<? super T> action);
    public abstract List<T> collect();
	public abstract T reduce(T identity, BinaryOperator<T> reducer);
}
