package util;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class Util {

    public static void mlog(Object... args) {
        final StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        final String methodName = elements[2].getMethodName();
        if (args.length == 0)
            hlog(methodName);
        else
            hlog(methodName + " " + Arrays.toString(args));

    }

    public static void hlog(String text) {
        final String LINE = "+------------------------------------------------";
        System.out.println(LINE);
        System.out.println("| " + text);
        System.out.println(LINE);
    }

    public static void tlog(String text, Object... args) {
        synchronized (System.out) {
            System.out.printf("[ %2d ] ", Thread.currentThread().getId());
            System.out.printf(text, args);
            System.out.println();
        }
    }

    public interface XRunnable {
        public abstract void run() throws Throwable;

        public static void xrun(XRunnable r) {
            try {
                r.run();
            } catch (RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public static void xrun(String msg, XRunnable r) {
            try {
                final long start = System.nanoTime();
                tlog("-> " + msg);
                r.run();
                final long end = System.nanoTime();
                tlog("<- " + msg + " (" + ((end - start) / 1_000_000) + ")");
            } catch (RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    public interface XCallable<T> {
        // public abstract T call() throws Throwable;

        public static <T> T xcall(Callable<T> c) {
            try {
                return c.call();
            } catch (RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public static <T> T xcall(String msg, Callable<T> c) {
            try {
                final long start = System.nanoTime();
                tlog("-> " + msg);
                final T result = c.call();
                final long end = System.nanoTime();
                tlog("<- " + msg + " --> " + result + " (" + ((end - start) / 1_000_000) + ")");
                return result;
            } catch (RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
}
