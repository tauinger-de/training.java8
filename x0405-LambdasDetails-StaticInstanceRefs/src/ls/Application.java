package ls;

import util.Features;

import static java.lang.System.out;

public class Application {

    private static int staticVar = 42;

    public static void main(String[] args) throws Exception {
        Features.print(Application.class);
        demo();
    }

    static void demo() throws Exception {
        final Runnable r = () -> out.println("Hello " + staticVar);
        Features.print(r.getClass());
    }
}
