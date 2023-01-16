package appl;


public class Application {
	
	
	public static void main(String[] args) {
		{
			Runnable r = () -> Foo.f();
			r.run();
		}
		{
			Runnable r = Foo::f;
			r.run();
		}
		// ------------------------------------------
		{
			Foo foo = new Foo();
			Runnable r = () -> foo.r();
			r.run();
		}
		{
			Foo foo = new Foo();
			Runnable r = foo::r;
			r.run();
		}
		// ------------------------------------------
		// ------------------------------------------
		{
			Mapper m = v -> Foo.g(v);
			int result = m.map(21);
			System.out.println(result);
		}
		{
			NumberMapper m = Foo::g;
			Number result = m.map(21);
			System.out.println(result);
		}
		{
			Mapper2 m = Foo::g;
			int result = m.map(21, 21);
			System.out.println(result);
		}
		// ------------------------------------------
		{
			Foo foo = new Foo();
			Mapper m = v -> foo.s(v);
			int result = m.map(21);
			System.out.println(result);
		}
		{
			Foo foo = new Foo();
			Mapper m = foo::s;
			int result = m.map(21);
			System.out.println(result);
		}
		// ------------------------------------------
		{
			Action<String> c = v -> System.out.println(v);
			c.execute("Hello");
		}
		{
			Action<String> c = System.out::println;
			c.execute("Hello");
		}
		// ------------------------------------------
		{
			Action<Integer> a = v -> new Bar(v);
			a.execute(42);
		}
		{
			Action<Integer> a = Bar::new;
			a.execute(42);
		}
	}
}
