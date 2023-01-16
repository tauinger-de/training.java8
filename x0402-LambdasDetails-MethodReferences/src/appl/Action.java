package appl;

@FunctionalInterface
public interface Action<T> {
	public void execute(T arg);
}
