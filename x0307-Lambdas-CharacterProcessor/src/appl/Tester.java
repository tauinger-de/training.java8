package appl;

@FunctionalInterface
public interface Tester<T> {
	public abstract boolean test(T value);
}
