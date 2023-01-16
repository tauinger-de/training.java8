package appl;

@FunctionalInterface
public interface Handler<T> {
	
	void handle(T value);
	
}
