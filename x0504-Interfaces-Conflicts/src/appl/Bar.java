package appl;

public interface Bar {
	public default void f() {
		System.out.println("Bar.f");
	}
}
