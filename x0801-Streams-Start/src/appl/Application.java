package appl;

import util.workviewer.Work;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static util.Util.mlog;
import static util.functional.proxies.TraceAroundAdvice.*;

public class Application {

    public static void main(String[] args) {
        demoForEach();
        demoFilter();
        demoMap();
        demoMapFilter();
        demoException();
        demoTrace();
    }

    static Stream<Integer> createStream() {
        final Collection<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add(i);
        return list.stream();
    }

    static void demoForEach() {
        mlog();
        Stream<Integer> stream = createStream();
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoFilter() {
        mlog();
        Stream<Integer> stream = createStream();
        stream
                .filter((x) -> x > 5)
                .filter(x -> x % 2 == 0)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoMap() {
        mlog();
        Stream<Integer> stream = createStream();
        stream
                .map(x -> x * 2)
                .map(x -> x + 1)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoMapFilter() {
        mlog();
        Stream<Integer> stream = createStream();
        stream
                .map(x -> x * 3)
                .filter(x -> x % 2 == 0)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoException() {
        mlog();
        Stream<Integer> stream = createStream();
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
        try {
            stream.forEach(x -> System.out.print(x + " "));
        } catch (IllegalStateException e) {
            System.out.println("Expected: " + e);
        }
    }

    static void demoTrace() {
        mlog();
        Work.useViewer();
        Work.viewer.start();

        Stream<Integer> stream = createStream();
        stream
                // .parallel()
                .peek(traceConsumer("peek", 600, x -> System.out.println(x)))
                .map(traceFunction("map", 600, x -> x * 3))
                .filter(tracePredicate("filter", 600, x -> x % 2 == 0))
                .forEach(traceConsumer("forEach", 600, x -> System.out.print(x + " ")));

        Work.viewer.stop();
    }

}
