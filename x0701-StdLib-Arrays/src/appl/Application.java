package appl;

import util.PerformanceRunner;

import java.util.Arrays;

import static java.lang.System.out;
import static util.Util.mlog;
import static util.Util.tlog;

public class Application {

    public static void main(String[] args) {
//		demoParallelSort();
//	demoParallelSetAll();
//		demoParallelPrefix();
//		demoSetAllPerformance(10, 10);
//		demoSetAllPerformance(10_000_000, 10);
//		demoSortPerformance(10, 10_000_000);
        demoSortPerformance(10_000_000, 10);
    }

    static void demoParallelSort() {
        mlog();
        final Integer[] array = new Integer[10000000];
        for (int i = 0; i < 10000000; i++)
            array[i] = 10000000 - i;
        Arrays.parallelSort(array, (v1, v2) -> {
            tlog("sort " + v1 + " " + v2);
            return v1.compareTo(v2);
        });
    }

    static void demoParallelSetAll() {
        mlog();
        final int[] array = new int[10];
        Arrays.parallelSetAll(array, index -> {
            tlog("setAll " + index);
            return index * 2;
        });
        for (int value : array)
            out.print(value + " ");
        out.println();
    }

    static void demoParallelPrefix() {
        mlog();
        final int[] array = new int[10];
        Arrays.setAll(array, index -> index + 1);
        Arrays.parallelPrefix(array, (left, right) -> {
            tlog("parallelPrefix: " + left + " " + right);
            return left + right;
        });
        for (int value : array)
            out.print(value + " ");
        out.println();
    }

    static void demoSetAllPerformance(int arraySize, int loops) {
        mlog();
        final int[] array = new int[arraySize];
        new PerformanceRunner().run("setAll " + array.length + " " + loops, loops,
                () -> Arrays.setAll(array, index -> {
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                            }
                            ;
                            return 2 * index;
                        }
                ));
        new PerformanceRunner().run("parallelSetAll " + array.length + " " + loops, loops,
                () -> Arrays.parallelSetAll(array, index -> {
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                            }
                            ;
                            return 2 * index;
                        }
                ));
    }

    static void initArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (array.length * Math.random());
        }
    }

    static void demoSortPerformance(int arraySize, int loops) {
        mlog();
        final int[] array = new int[arraySize];
        new PerformanceRunner().run("sort " + array.length + " " + loops, loops,
                () -> initArray(array), () -> Arrays.sort(array));
        new PerformanceRunner().run("parallelSort " + array.length + " " + loops, loops,
                () -> initArray(array), () -> Arrays.parallelSort(array));

    }


}
