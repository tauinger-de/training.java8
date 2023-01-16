package appl;

import util.PerformanceRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static util.Util.XRunnable.xrun;

public class Application {

    private static int SIZE = 1_000;
    private static int TIMES = 1;
    private static int SLEEPTIME = 2;

    public static void main(String[] args) {
        boolean simple = false;
        boolean useThreadPoolProperty = false;
        if (simple) {
            if (!useThreadPoolProperty) {
                demoStandard(SIZE, SLEEPTIME);
                demoForkJoinPool(SIZE, SLEEPTIME);
            } else
                demoThreadPoolProperty(SIZE, SLEEPTIME);
        } else {
            if (!useThreadPoolProperty) {
                demoStandard(SIZE, SLEEPTIME, TIMES);
                demoForkJoinPool(SIZE, SLEEPTIME, TIMES);
            } else
                demoThreadPoolProperty(SIZE, SLEEPTIME, TIMES);
        }
    }

    static void demoStandard(int size, int sleepTime) {
        mlog("size = " + size + ", sleeptime = " + sleepTime);
        System.out.println("availableProcessors = " + Runtime.getRuntime().availableProcessors());
        final Set<Thread> threads = new HashSet<>();
        IntStream.range(1, size)
                .parallel()
                .peek(x -> xrun(() -> Thread.sleep(sleepTime)))
                .forEach(x -> threads.add(Thread.currentThread()));
        System.out.println("Used Threads: " + threads.size());
    }

    static void demoForkJoinPool(int size, int sleepTime) {
        mlog("size = " + size + ", sleeptime = " + sleepTime);
        final ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        final Set<Thread> threads = new HashSet<>();
        final Future<?> future = forkJoinPool.submit(() ->
                IntStream.range(1, size)
                        .parallel()
                        .peek(x -> xrun(() -> Thread.sleep(sleepTime)))
                        .forEach(x -> threads.add(Thread.currentThread())));
        xrun(() -> future.get());
        System.out.println("Used Threads: " + threads.size());
    }

    static void demoThreadPoolProperty(int size, int sleepTime) {
        mlog("size = " + size + ", sleeptime = " + sleepTime);
        final Set<Thread> threads = new HashSet<>();
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        IntStream.range(1, size)
                .parallel()
                .peek(x -> xrun(() -> Thread.sleep(sleepTime)))
                .forEach(x -> threads.add(Thread.currentThread()));
        System.out.println("Used Threads: " + threads.size());
    }

    // using PerformanceRunner...

    static void demoStandard(int size, int sleepTime, int times) {
        mlog("size = " + size + ", sleeptime = " + sleepTime + ", times = " + times);
        final Set<Thread> threads = new HashSet<>();
        new PerformanceRunner().run("", times, () -> {
            IntStream.range(1, size)
                    .parallel()
                    .peek(x -> xrun(() -> Thread.sleep(sleepTime)))
                    .forEach(x -> threads.add(Thread.currentThread()));
        });
        System.out.println("Used Threads: " + threads.size());
    }

    static void demoForkJoinPool(int size, int sleepTime, int times) {
        mlog("size = " + size + ", sleeptime = " + sleepTime + ", times = " + times);
        final ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        final Set<Thread> threads = new HashSet<>();
        new PerformanceRunner().run("", times, () -> {
            final Future<?> future = forkJoinPool.submit(() ->
                    IntStream.range(1, size)
                            .parallel()
                            .peek(x -> xrun(() -> Thread.sleep(sleepTime)))
                            .forEach(x -> threads.add(Thread.currentThread())));
            xrun(() -> future.get());
        });
        System.out.println("Used Threads: " + threads.size());
    }

    static void demoThreadPoolProperty(int size, int sleepTime, int times) {
        mlog("size = " + size + ", sleeptime = " + sleepTime + ", times = " + times);
        final Set<Thread> threads = new HashSet<>();
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        new PerformanceRunner().run("", times, () -> {
            IntStream.range(1, size)
                    .parallel()
                    .peek(x -> xrun(() -> Thread.sleep(sleepTime)))
                    .forEach(x -> threads.add(Thread.currentThread()));
        });
        System.out.println("Used Threads: " + threads.size());
    }

}
