package appl;

public class Application {
	public static void main(String[] args) {
		Foo.printX();
		Foo foo = new FooImpl();
		System.out.println(Foo.x);
		foo.f();
	}
}
