package appl;

import util.workviewer.Work;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static util.Util.mlog;
import static util.functional.proxies.TraceAroundAdvice.*;

public class Application {

    public static void main(String[] args) throws Exception {

        demo1();
        demo2();
    }

    static <T, R> void execute(
            Supplier<T> supplier,
            Function<T, R> function,
            Consumer<R> consumer,
            Runnable runnable) {
        final T t = supplier.get();
        final R r = function.apply(t);
        consumer.accept(r);
        runnable.run();
    }

    static void demo1() {
        mlog();
        execute(
                () -> 21,
                x -> 2 * x,
                x -> System.out.println(x),
                () -> System.out.println("FIN"));
    }

    static void demo2() {
        mlog();
        Work.useViewer();
        Work.viewer.start();

        execute(
                traceSupplier("s", 1000, () -> 21),
                traceFunction("f", 2000, x -> 2 * x),
                traceConsumer("c", 1500, x -> System.out.println(x)),
                traceRunnable("R", 2500, () -> System.out.println("FIN")));

        Work.viewer.stop();

    }
}
