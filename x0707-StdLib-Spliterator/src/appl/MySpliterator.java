package appl;

import java.util.Spliterator;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class MySpliterator<T> implements Spliterator<T> {

    private final T[] elements;

    public MySpliterator(T[] elements) {
        this.elements = elements;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        return false;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
