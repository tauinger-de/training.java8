package ex2;

@FunctionalInterface
public interface Reallocator {

    int newSize(int oldSize);

}
