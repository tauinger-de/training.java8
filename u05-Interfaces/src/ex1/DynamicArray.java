package ex1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<T> implements Iterable<T> {

    private T[] elements;

    private int size;

    @SuppressWarnings("unchecked")
    public DynamicArray(Class<T> contentType, int initialSize) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException("Initial size must be > 0");
        }
        elements = (T[]) Array.newInstance(contentType, initialSize);
    }

    public void add(T element) {
        this.ensureCapacity();
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

    private void ensureCapacity() {
        if (this.elements.length == size) {
            this.elements = Arrays.copyOf(elements, this.size * 2);
            System.out.println("Expanded underlying array to size " + this.elements.length);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    class IteratorImpl implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            if (hasNext()) {
                // das Inkrementieren durch ++ erfolgt "nachgelagert", d.h. es wird erst
                // das Element mit dem alten Wert von `current` ausgelesen, dann erhöht
                return elements[current++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
