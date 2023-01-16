package appl;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")

class Array2<T> {
	private final T[] elements;

	public Array2(T[] elements) {
		final int n = elements.length;
		this.elements = (T[])new Object[n];
		for (int i = 0; i < n; i++)
			this.elements[i] = elements[i];
	}

	public Spliterator<T> spliterator() {
		return new ArraySpliterator<>(this.elements, 0, this.elements.length);
	}

	static class ArraySpliterator<T> extends Spliterators.AbstractSpliterator<T> {
		private final T[] array;
		private int origin; 
		private final int fence; // max-index + 1

		ArraySpliterator(T[] array, int origin, int fence) {
			super(fence-origin, 0);
			this.array = array;
			this.origin = origin;
			this.fence = fence;
		}

		public boolean tryAdvance(Consumer<? super T> action) {
			if (this.origin >= this.fence)
				return false;
			action.accept((T) this.array[this.origin]);
			this.origin++;
			return true;
		}
	}
}
