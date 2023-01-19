package appl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
        demoMap();
        demoMapToInt();
        demoMapToDouble();
        demoFlatMap();
        demoFlatMapToInt();
        demoFilter();
        demoPeek();
        demoDistinct();
        demoSorted();
        demoSkip();
        demoLimit();
        demoCombination1();
        demoCombination2();
    }

    @SuppressWarnings("SimplifyStreamApiCallChains")
    static void demoMap() {
        mlog();
        Arrays.asList("red", "green", "blue")
                .stream()
                .map(String::length)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoMapToInt() {
        mlog();
        Stream.of("red", "green", "blue")
                .mapToInt(String::length)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoMapToDouble() {
        mlog();
        Stream.of("red", "green", "blue")
                .mapToDouble(s -> s.length() * 0.5)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    /**
     * Hier streamen wir eine Liste von Listen. FlatMap kann die Elemente dieser Listen auf einen
     * Stream reduzieren
     */
    @SuppressWarnings("Convert2MethodRef")
    static void demoFlatMap() {
        mlog();
        final List<String> list1 = Arrays.asList("red", "green", "blue");
        final List<String> list2 = Arrays.asList("rot", "grün", "blau");
        final List<List<String>> list = Arrays.asList(list1, list2);

        list.stream()
                .flatMap((List<String> l) -> l.stream())
                .forEach(s -> System.out.print(s + " "));
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
        Stream.of("red", "green", "blue")
                .filter(s -> s.length() > 3)
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    /**
     * Peek erlaubt die Vorschaltung eines Consumers je Element (dies ist keine terminale Operation
     * im Gegensatz zu foreach)
     */
    static void demoPeek() {
        mlog();
        Stream.of("red", "green", "blue")
                .peek(s -> System.out.print(s.length() + ":"))
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static void demoDistinct() {
        mlog();
        Stream.of("red", "green", "red", "blue", "green")
                .distinct()
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static void demoSorted() {
        mlog();
        Stream.of("red", "green", "blue")
                .sorted()
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static void demoSkip() {
        mlog();
        Stream.of(10, 20, 30, 40)
                .skip(2)
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    static void demoLimit() {
        mlog();
        Stream.of(10, 20, 30, 40)
                .limit(3)
                .forEach(s -> System.out.print(s + " "));
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
    //@formatter:on
}
