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

    /**
     * Hilfsmethode zur Erzeugung eines Streams
     */
    private static Stream<Integer> createStream() {
        final Collection<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
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
        createStream()
                .filter(x -> x > 5)
                .filter(x -> x % 2 == 0)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoMap() {
        mlog();
        createStream()
                .map(x -> x * 2)
                .map(x -> x + 1)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    static void demoMapFilter() {
        mlog();
        createStream()
                .map(x -> x * 3)
                .filter(x -> x % 2 == 0)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    /**
     * Zeigt, dass ein Stream nach Aufruf einer terminalen Operation nicht mehr verwendet werden kann.
     */
    @SuppressWarnings("DataFlowIssue")
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

        createStream()
                //  .parallel()
                .peek(traceConsumer("peek", 600, System.out::println))
                .map(traceFunction("map", 600, x -> x * 3))
                .filter(tracePredicate("filter", 600, x -> x % 2 == 0))
                .forEach(traceConsumer("forEach", 600, x -> System.out.print(x + " ")));

        Work.viewer.stop();
    }

}
