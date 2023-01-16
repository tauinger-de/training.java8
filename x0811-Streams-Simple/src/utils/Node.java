package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

abstract class Node<P, T> implements Stream<T> { // P: previous type T: own type

	protected abstract T get();
	
	protected final Node<?,P> previous;
	
	protected Node(Node<?, P> previous) {
		this.previous = previous;
	}
	
	@Override
	public Stream<T> filter(Predicate<? super T> predicate) {
		return new FilterNode<T>(this, predicate);
	}

	@Override
	public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
		return new MapNode<T,R>(this, mapper);
	}

	@Override
    public Stream<T> peek(Consumer<? super T> action) {
		return new PeekNode<T>(this, action);
	}
	
	@Override
	public void forEach(Consumer<? super T> action) {
		for(T elem = this.get(); elem != null; elem = this.get()) {
			action.accept(elem);
		}
	}

	@Override
	public List<T> collect() {
		final List<T> result = new ArrayList<>();
		for(T elem = this.get(); elem != null; elem = this.get()) {
			result.add(elem);
		}
		return result;
	}

	@Override
	public T reduce(T identity, BinaryOperator<T> reducer) {
		T result = identity;
		for(T elem = this.get(); elem != null; elem = this.get()) {
			result = reducer.apply(result, elem);
		}
		return result;
	}
}
