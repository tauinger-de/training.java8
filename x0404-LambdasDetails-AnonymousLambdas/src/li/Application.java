package li;

import util.Features;

import java.lang.reflect.Constructor;

import static java.lang.System.out;

public class Application {

    public static void main(String[] args) throws Exception {
        Features.print(Application.class);
        final Application appl = new Application();
        appl.demo();
    }

    void demo() throws Exception {
        final Runnable r = () -> out.println("Hello");
        Features.print(r.getClass());

        // // this doesn't word:
//		 final Class<? extends Runnable> cls = r.getClass();
//		 final Runnable rr= cls.newInstance();
//		 rr.run();

        final Class<? extends Runnable> cls = r.getClass();
        final Constructor<? extends Runnable> ctor =
                cls.getDeclaredConstructor();
        ctor.setAccessible(true);
        final Runnable rr = ctor.newInstance();
        rr.run();
    }
}
