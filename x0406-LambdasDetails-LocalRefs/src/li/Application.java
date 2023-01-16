package li;

import static java.lang.System.out;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import util.Features;

public class Application {

	String hello = "Hello";

	public static void main(String[] args) throws Exception {
		Application appl = new Application();
		appl.demo();
	}

	void demo() throws Exception {
		int foo = 42;
		final int bar = 77;
		final Runnable r = () -> out.println(this.hello + " " + foo + " " + bar);
		Features.print(r.getClass());

		Class<? extends Runnable> cls = r.getClass();
		
		Field field0 = cls.getDeclaredField("arg$1");
		field0.setAccessible(true);
		Object obj0 = field0.get(r);
		out.println(obj0 == this);
		
		Field field1 = cls.getDeclaredField("arg$2");
		field1.setAccessible(true);
		Object obj1 = field1.get(r);
		out.println(obj1);
		
		Method m = cls.getDeclaredMethod("get$Lambda", Application.class, int.class);
		m.setAccessible(true);
		Runnable rr = (Runnable)m.invoke(r, this, 43);
		rr.run();
		
	}
}
