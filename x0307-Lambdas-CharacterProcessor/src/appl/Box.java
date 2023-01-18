package appl;

public class Box<T> {

    public T value;

    public Box(T initialValue) {
        this.value = initialValue;
    }

    public String toString() {
        return this.value.toString();
    }
}
