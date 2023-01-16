package appl;

import util.Features;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
//		demoMap();
//		demoMapToInt();
//		demoMapToDouble();
//		demoFlatMap();
//		demoFlatMapToInt();
//		demoFilter();
//		demoPeek();
//		demoDistinct();
//		demoSorted();
//		demoSkip();
//		demoLimit();
//		demoCombination1();		
        demoCombination2();
//		demoCombination3();
//		demoInternal();
    }

    static void demoMap() {
        mlog();
        final List<String> list = Arrays.asList("red", "green", "blue");
        Stream<Integer> stream = list.stream().map(s -> s.length());
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoMapToInt() {
        mlog();
        final List<String> list = Arrays.asList("red", "green", "blue");
        IntStream stream = list.stream().mapToInt(s -> s.length());
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoMapToDouble() {
        mlog();
        final List<String> list = Arrays.asList("red", "green", "blue");
        DoubleStream stream = list.stream().mapToDouble(s -> s.length() * 0.5);
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoFlatMap() {
        mlog();
        final List<String> list1 = Arrays.asList("red", "green", "blue");
        final List<String> list2 = Arrays.asList("rot", "gruen", "blau");
        final List<List<String>> list = Arrays.asList(list1, list2);

        Stream<String> stream = list.stream().flatMap((List<String> l) -> l.stream());
        stream.forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static void demoFlatMapToInt() {
        mlog();
        final int[] array1 = new int[]{10, 20, 30};
        final int[] array2 = new int[]{40, 50};
        final int[] array3 = new int[]{60};
        final int[][] array = new int[][]{array1, array2, array3};

        IntStream stream = Arrays.stream(array).flatMapToInt((int[] a) -> Arrays.stream(a));
        stream.forEach((int i) -> System.out.print(i + " "));
        System.out.println();
    }

    static void demoFilter() {
        mlog();
        Stream<String> stream = Arrays.asList("red", "green", "blue").stream();
        stream.filter(s -> s.length() > 3).forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static void demoPeek() {
        mlog();
        Stream<String> stream = Arrays.asList("red", "green", "blue").stream();
        stream.peek(s -> System.out.print(s.length() + ":")).forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static void demoDistinct() {
        mlog();
        Stream<String> stream = Arrays.asList("red", "green", "red", "blue", "green").stream();
        stream.distinct().forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static void demoSorted() {
        mlog();
        Stream<String> stream = Arrays.asList("red", "green", "blue").stream();
        stream.sorted().forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static void demoSkip() {
        mlog();
        Stream<Integer> stream = Arrays.asList(10, 20, 30, 40).stream();
        stream.skip(2).forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static void demoLimit() {
        mlog();
        Stream<Integer> stream = Arrays.asList(10, 20, 30, 40).stream();
        stream.limit(3).forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static void demoCombination1() {
        mlog();
        Stream<Integer> stream = Stream.of(33, 55, 44, 11, 22, 66);
        stream
                .skip(1)
                .limit(4)
                .filter(x -> x % 2 == 0)
                .sorted()
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    //@formatter:off
    static void demoCombination2() {
        mlog();
        try {
            Stream<Integer> stream = Stream.of(33, 55, 44, 11, 22, 66);
            stream
                    .skip(1)
                    .limit(4);
            stream
                    .filter(x -> x % 2 == 0)
                    .sorted()
                    .forEach(s -> System.out.print(s + " "));
            System.out.println();
        } catch (Exception e) {
            System.out.println("Expected: " + e.getMessage());
        }
    }

    static void demoCombination3() {
        mlog();
        Stream<Integer> stream = Stream.of(33, 55, 44, 11, 22, 66);
        stream = stream
                .skip(1)
                .limit(4);
        stream
                .filter(x -> x % 2 == 0)
                .sorted()
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
    }
    //@formatter:on

    static void demoInternal() {
        mlog();
        Stream<Integer> stream = Stream.of(33, 55, 44, 11, 22, 66);
        Features.printInheritance(stream);
        stream = stream.map(x -> x + 1);
        Features.printInheritance(stream);
        stream = stream.filter(x -> x % 2 == 0);
        Features.printInheritance(stream);
        stream.forEach(s -> System.out.print(s + " "));
        System.out.println();
    }
}
