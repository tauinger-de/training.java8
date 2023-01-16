package appl;

@FunctionalInterface
public interface Foo {
	void f();
	public default void g() {
		System.out.println("g");
	}
//	default final void h() {
//		System.out.println("h");
//	}
}
