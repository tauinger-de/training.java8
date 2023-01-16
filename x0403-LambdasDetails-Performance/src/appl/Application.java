package appl;

import util.PerformanceRunner;

import static java.lang.System.out;

// try JVM-Arg -server!

public class Application {

    private static final long TIMES = 2_000_000_000;
    private static final int LOOPS = 10;

    private static long n = 0;

    public static void main(String[] args) throws Exception {
        out.println("Performace-Test: this will take some time ...");
        PerformanceRunner runner = new PerformanceRunner();

        long duration1;
        long duration2;
        for (int i = 0; i < LOOPS; i++) {
            // profile anonymous class
            {
                Runnable r1 = new Runnable() {
                    public void run() {
                        n++;    // increment static var just to do something
                    }
                };
                duration1 = runner.run("anonymous class", TIMES, r1);
            }

            // profile anonymous class
            {
                Runnable r2 = () -> n++;
                duration2 = runner.run("lambda", TIMES, r2);
            }

            // dump difference
            out.printf("Anonymous class was %d ms faster than lambdas\n", duration2 - duration1);
        }
    }
}

