package utils;

import java.util.function.Consumer;

class PeekNode<T> extends Node<T,T> {
	final Consumer<? super T> action;
	public PeekNode(Node<?, T> previous, Consumer<? super T> action) {
		super(previous);
		this.action = action;
	}
	@Override
	protected T get() {
		T elem = this.previous.get();
		if (elem != null)
			this.action.accept(elem);
		return elem;
	}
}

