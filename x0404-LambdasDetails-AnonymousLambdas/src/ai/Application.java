package ai;

import static java.lang.System.out;

import java.lang.reflect.Constructor;

import util.Features;

public class Application {

	public static void main(String[] args) throws Exception {
		Features.print(Application.class);
		final Application appl = new Application();
		appl.demo();
	}

	void demo() throws Exception {
		final Runnable r = new Runnable() {
			public void run() {
				out.println("Hello");
			}
		};
		Features.print(r.getClass());
		
		// this doesn't word:
//		final Class<? extends Runnable> cls = r.getClass();
//		final Runnable rr= cls.newInstance();
//		rr.run();

		final Class<? extends Runnable> cls = r.getClass();
		 final Constructor<? extends Runnable> ctor =
				 cls.getDeclaredConstructor(Application.class);
		 final Runnable rr = ctor.newInstance(this);
		 rr.run();
	}
}
