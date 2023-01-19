package appl;

import util.PerformanceRunner;

import java.util.Arrays;

import static java.lang.System.out;
import static util.Util.mlog;
import static util.Util.tlog;

@SuppressWarnings("MismatchedReadAndWriteOfArray")
public class Application {

    public static void main(String[] args) {
        demoParallelSort();
        demoParallelSetAll();
        demoParallelPrefix();
        demoSetAllPerformance(100, 20);
        demoSetAllPerformance(100_000_000, 10);
        demoSortPerformance(10, 10_000_000);
        demoSortPerformance(10_000_000, 10);
    }

    /**
     * Sortieren eines Arrays mit mehreren Threads in parallel.
     */
    static void demoParallelSort() {
        mlog();
        final Integer[] array = new Integer[100_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        Arrays.parallelSort(array, (v1, v2) -> {
            tlog("comparing " + v1 + " with " + v2);
            return v1.compareTo(v2);
        });
    }

    /**
     * Befüllt ein Array mit mehreren Threads parallel.
     */
    static void demoParallelSetAll() {
        mlog();
        final int[] array = new int[10];
        Arrays.parallelSetAll(array, index -> {
            tlog("setting index " + index);
            return index * 2;
        });
        out.println(Arrays.toString(array));
    }

    /**
     * Aktualisiert jedes Element des Arrays auf Basis des aktuellen Werts und des Vorgängerwertes
     * unter Nutzung des angegebenen Operator-Lambdas.
     */
    static void demoParallelPrefix() {
        mlog();
        final int[] array = new int[10];
        Arrays.setAll(array, index -> index + 1);
        out.println(Arrays.toString(array));
        Arrays.parallelPrefix(array, (left, right) -> {
            tlog("parallelPrefix: " + left + " " + right);
            return left + right;
        });
        out.println(Arrays.toString(array));
    }

    /**
     * Führt eine single-threaded und eine multi-threaded Befüllung durch und misst die Laufzeit.
     */
    static void demoSetAllPerformance(int arraySize, int loops) {
        mlog();
        final int[] array = new int[arraySize];
        new PerformanceRunner().run(
                "setAll: " + array.length + " items in " + loops + " loops",
                loops,
                () -> Arrays.setAll(array, index -> 2 * index)
        );
        new PerformanceRunner().run(
                "parallelSetAll: " + array.length + " items in " + loops + " loops",
                loops,
                () -> Arrays.parallelSetAll(array, index -> 2 * index)
        );
    }

    /**
     * Führt eine single-threaded und eine multi-threaded Sortierung durch und misst die Laufzeit.
     */
    static void demoSortPerformance(int arraySize, int loops) {
        mlog();
        final int[] array = new int[arraySize];
        new PerformanceRunner().run(
                "sort: " + array.length + " items in " + loops + " loops",
                loops,
                () -> initArray(array),
                () -> Arrays.sort(array)
        );
        new PerformanceRunner().run(
                "parallelSort: " + array.length + " items in " + loops + " loops",
                loops,
                () -> initArray(array),
                () -> Arrays.parallelSort(array)
        );
    }

    static void initArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (array.length * Math.random());
        }
    }

}
