package parser.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Node<T> {
	private static class Leaf<T> extends Node<T> {
		final T value;
		Leaf(T value) {
			this.value = value;
		}
		void collect(List<T> list) {
			list.add(this.value);
		}
	}
	private static class Pair<T> extends Node<T> {
		final Node<T> left;
		final Node<T> right;
		Pair(Node<T> left, Node<T> right) {
			this.left = left;
			this.right = right;
		}
		void collect(List<T> list) {
			this.left.collect(list);
			this.right.collect(list);
		}
	}
	public static <T> Node<T> of(T value) {
		return new Leaf<T>(value);
	}
	public static <T> Node<T> of(Node<T> left, Node<T> right) {
		return new Pair<T>(left, right);
	}
	public List<T> list() {
		final List<T> list = new ArrayList<>();
		this.collect(list);
		return Collections.unmodifiableList(list);
	}
	abstract void collect(List<T> list);
}
