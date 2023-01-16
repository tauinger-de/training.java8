package ai;

import util.Features;

import static java.lang.System.out;

public class Application {

    private int instanceVar = 42;

    public static void main(String[] args) throws Exception {
        Features.print(Application.class);
        Application appl = new Application();
        appl.demo();
    }

    void demo() throws Exception {
        final Runnable r = new Runnable() {
            public void run() {
                out.println("Hello " + Application.this.instanceVar);
            }
        };
        Features.print(r.getClass());
    }
}
