package li;

import static java.lang.System.out;

import java.lang.reflect.Method;

import util.Features;

public class Application {

	private int instanceVar = 42;
	
	public static void main(String[] args) throws Exception {
		Features.print(Application.class);
		Application appl = new Application();
		appl.demo();
	}

	void demo() throws Exception {
		final Runnable r = () -> out.println("Hello " + this.instanceVar);
		Features.print(r.getClass());
		
		Method m = r.getClass().getDeclaredMethod("get$Lambda", Application.class);
		m.setAccessible(true);
		Runnable rr = (Runnable) m.invoke(r, this);
		rr.run();
		out.println(r == rr);
		out.println(r.getClass() == rr.getClass());
		
	}
}
