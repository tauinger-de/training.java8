package utils;

import java.util.Iterator;

class HeadNode<T> extends Node<Void, T> {
	protected Iterator<T> iterator;
	public HeadNode(Iterable<T> iterable) {
		super(null);
		this.iterator = iterable.iterator();
	}
	@Override
	protected T get() {
		return this.iterator.hasNext() ? this.iterator.next() : null;
	}
}
