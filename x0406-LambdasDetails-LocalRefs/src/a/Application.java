package a;

import static java.lang.System.out;

import java.lang.reflect.Field;

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
		final Runnable r = new Runnable() {
			public void run() {
				out.println(Application.this.hello + " " + foo + " " + bar);
			}
		};
		Features.print(r.getClass());
		
		Class<? extends Runnable> cls = r.getClass();
		Field field0 = cls.getDeclaredField("this$0");
		Object obj0 = field0.get(r);
		out.println(obj0 == this);
		Field field1 = cls.getDeclaredField("val$foo");
		field1.setAccessible(true);
		Object obj1 = field1.get(r);
		out.println(obj1);
	}
}
