package utils;

import java.util.function.Predicate;

class FilterNode<T> extends Node<T,T> {
	final Predicate<? super T> predicate;
	public FilterNode(Node<?, T> previous, Predicate<? super T> predicate) {
		super(previous);
		this.predicate = predicate;
	}
	@Override
	protected T get() {
		T elem = this.previous.get();
		while (elem != null && ! this.predicate.test(elem))
			elem = this.previous.get();
		return elem;
	}
}

