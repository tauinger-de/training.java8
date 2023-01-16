package ex2;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<T> {

    private T[] elements;

    private int size;

    @SuppressWarnings("unchecked")
    public DynamicArray(Class<T> contentType, int initialSize) {
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
}
