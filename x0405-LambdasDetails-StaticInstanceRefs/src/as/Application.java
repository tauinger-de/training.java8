package as;

import static java.lang.System.out;
import util.Features;

public class Application {

	private static int staticVar = 42;
	
	public static void main(String[] args) throws Exception {
		Features.print(Application.class);
		demo();
	}

	static void demo() throws Exception {
		final Runnable r = new Runnable() {
			public void run() {
				out.println("Hello " + Application.staticVar);
			}
		};
		Features.print(r.getClass());
	}
}
