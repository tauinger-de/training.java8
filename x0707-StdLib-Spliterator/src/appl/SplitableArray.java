package appl;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;

class SplitableArray<T> {

    private final T[] elements;

    public SplitableArray(T[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length); // independent copy
    }

    public Spliterator<T> spliterator() {
        return new ArraySpliterator<>(this.elements, 0, this.elements.length);
    }

    static class ArraySpliterator<T> implements Spliterator<T> {

        private final T[] array;
        private int origin; // the inclusive start index
        private final int fence; // the exclusive end index

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
            if (this.origin >= this.fence) {
                return false;
            }
            action.accept(this.array[this.origin]);
            this.origin++;
            return true;
        }

        public Spliterator<T> trySplit() {
            int start = this.origin;
            int middle = (start + this.fence) / 2;
            if (start >= middle) {
                return null;
            }
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
