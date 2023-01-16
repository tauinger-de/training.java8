package appl;

import java.util.Spliterator;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")

class Array<T> {
	private final T[] elements;

	public Array(T[] elements) {
		final int n = elements.length;
		this.elements = (T[])new Object[n];
		for (int i = 0; i < n; i++)
			this.elements[i] = elements[i];
	}

	public Spliterator<T> spliterator() {
		return new ArraySpliterator<>(this.elements, 0, this.elements.length);
	}

	static class ArraySpliterator<T> implements Spliterator<T> {
		private final T[] array;
		private int origin; 
		private final int fence; // max-index + 1

		ArraySpliterator(T[] array, int origin, int fence) {
			this.array = array;
			this.origin = origin;
			this.fence = fence;
		}

//		public void forEachRemaining(Consumer<? super T> action) {
//			while (this.origin < this.fence) {
//				action.accept(this.array[this.origin]);
//				this.origin++;
//			}
//		}

		public boolean tryAdvance(Consumer<? super T> action) {
			if (this.origin >= this.fence)
				return false;
			action.accept((T) this.array[this.origin]);
			this.origin++;
			return true;
		}

		public Spliterator<T> trySplit() {
			int start = this.origin; 
			int middle = (start + this.fence) / 2;
			if (start >= middle)
				return null;
			this.origin = middle; 
			return new ArraySpliterator<>(this.array, start, middle);
		}

		public long estimateSize() {
			return this.fence - this.origin;
		}

		public int characteristics() {
			return 0;
		}
	}
}
