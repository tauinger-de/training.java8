package appl;

import java.io.Serializable;

@FunctionalInterface
public interface Foo<T> extends Serializable {
	public void run(T value);
}
