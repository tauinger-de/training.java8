package ex1;

import java.util.Arrays;

public class Array<T> {
	@SuppressWarnings("unchecked")
	private T[] elements = (T[]) new Object[2];
	private int size;
	
	public void add(T element) {
		this.ensureCapcity();
		this.elements[this.size] = element;
		this.size++;
	}
	public int size() {
		return this.size;
	}
	
	public T get(int index) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		return this.elements[index];
	}
	private void ensureCapcity() {
		if (this.elements.length == size) {
			this.elements = Arrays.copyOf(elements, this.size * 2);
		}
	}
}
