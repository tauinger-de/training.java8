package appl;

// Yet Another Comparator...
public interface YAC<T> {

    boolean isEqual(T v0, T v1);

    boolean isGreaterThan(T v0, T v1);

    default boolean isLessThan(T v0, T v1) {
        return !this.isGreaterOrEqual(v0, v1);
    }

    default boolean isGreaterOrEqual(T v0, T v1) {
        return this.isGreaterThan(v0, v1) || this.isEqual(v0, v1);
    }

    default boolean isLessThanOrEqual(T v0, T v1) {
        return this.isEqual(v0, v1) || this.isLessThan(v0, v1);
    }
}
