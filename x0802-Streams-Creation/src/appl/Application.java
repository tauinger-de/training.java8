package appl;

import util.PerformanceRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
        demoCollectionStream();
        demoArraysStream();
        demoIntStream();
        demoLongStream();
        demoDoubleStream();
        demoStreamOf();
        demoIntStreamRange();
        demoIntStreamIterate();
        demoStreamIterate();
        demoPerformanceStream();
        demoPerformanceIntStream();
    }

    static void demoCollectionStream() {
        mlog();
        final List<Integer> list = Arrays.asList(1, 2, 3);
        Stream<Integer> stream = list.stream();
        System.out.println(stream.getClass().getName());
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoArraysStream() {
        mlog();
        final Integer[] array = new Integer[]{1, 2, 3};
        Stream<Integer> stream = Arrays.stream(array);
        System.out.println(stream.getClass().getName());
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoIntStream() {
        mlog();
        final int[] array = new int[]{1, 2, 3};
        IntStream stream = Arrays.stream(array);
        System.out.println(stream.getClass().getName());
        stream.forEach(x -> System.out.print(x + " "));
        // stream.forEach((int x) -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoLongStream() {
        mlog();
        final long[] array = new long[]{1, 2, 3};
        LongStream stream = Arrays.stream(array);
        System.out.println(stream.getClass().getName());
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoDoubleStream() {
        mlog();
        final double[] array = new double[]{1.0, 2.0, 3.0};
        DoubleStream stream = Arrays.stream(array);
        System.out.println(stream.getClass().getName());
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoStreamOf() {
        mlog();
        Stream<Integer> stream = Stream.of(1, 2, 3);
        System.out.println(stream.getClass().getName());
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoIntStreamRange() {
        mlog();
        IntStream stream = IntStream.range(1, 4);
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoIntStreamIterate() {
        mlog();
        final IntStream stream = IntStream.iterate(10, x -> x + 2).limit(5);
        stream.forEach((int x) -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoStreamIterate() {
        mlog();
        final Stream<Integer> stream = Stream.iterate(10, x -> x + 2).limit(5);
        stream.forEach((Integer x) -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoStreamEmpty() {
        mlog();
        Stream<Integer> stream = Stream.empty();
        stream.forEach((Integer x) -> System.out.print(x + " "));
        System.out.println();
    }

    static class IntHolder {
        public int value;
    }

    static final int size = 1_000_000;
    static final int loops = 1_000;

    // VM-arg: -Server

    static void demoPerformanceStream() {
        mlog();
        final PerformanceRunner runner = new PerformanceRunner();
        final Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++)
            array[i] = i;
        final IntHolder h = new IntHolder();
        runner.run("Integer", loops, () -> {
            final Stream<Integer> stream = Arrays.stream(array);
            stream.forEach((Integer x) -> h.value += x);
        });
        System.out.println(h.value);
    }

    static void demoPerformanceIntStream() {
        mlog();
        final PerformanceRunner runner = new PerformanceRunner();
        final int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = i;
        final IntHolder h = new IntHolder();
        runner.run("int", loops, () -> {
            final IntStream stream = Arrays.stream(array);
            stream.forEach((int x) -> h.value += x);
        });
        System.out.println(h.value);
    }


}
