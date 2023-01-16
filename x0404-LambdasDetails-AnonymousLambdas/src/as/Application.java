package as;

import static java.lang.System.out;
import util.Features;

public class Application {

	public static void main(String[] args) throws Exception {
		Features.print(Application.class);
		demo();
	}

	static void demo() throws Exception {
		final Runnable r = new Runnable() {
			public void run() {
				out.println("Hello");
			}
		};
		Features.print(r.getClass());

		final Class<? extends Runnable> cls = r.getClass();
		final Runnable rr= cls.newInstance();
		rr.run();

//		 final Class<? extends Runnable> cls = r.getClass();
//		 final Constructor<? extends Runnable> ctor =
//		 cls.getDeclaredConstructor();
//		 ctor.setAccessible(true);
//		 final Runnable rr = ctor.newInstance();
//		 rr.run();

	}
}
