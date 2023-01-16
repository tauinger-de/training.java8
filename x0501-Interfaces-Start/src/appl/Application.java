package appl;

import static java.lang.System.out;

public class Application {
	public static void main(String[] args) {
		Foo foo = new FooImpl();
		out.println(Foo.x);
		out.println(Foo.y);
		out.println(Foo.z);
		foo.f();
		foo.g();
		foo.h();
		
	}
}
