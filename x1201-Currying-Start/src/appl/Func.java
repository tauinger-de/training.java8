package appl;

@FunctionalInterface
public interface Func<T, R> {
    public abstract R apply(T value);

    public static <T, R> R apply(Func<T, Func<T, R>> func, T v1, T v2) {
        return func.apply(v1).apply(v2);
    }

    public default R apply(T v1, T v2) {
        Func<T, Func<T, R>> func = (Func<T, Func<T, R>>) this;
        Object result = func.apply(v1);
        if (!(result instanceof Func))
            throw new RuntimeException(result + " isn't a Func");
        Func<T, R> ff = (Func<T, R>) result;
        return ff.apply(v2);
    }
}
