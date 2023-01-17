package appl;

import java.util.*;

import static java.lang.System.out;
import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
        demoForEach();
        demoRemoveIf();
        demoSort();
        demoReplaceAll();
    }

    static void demoForEach() {
        mlog();
        Iterable<Integer> list = Arrays.asList(20, 40, 10, 30);

        // foreach mit Methoden-Ref
        list.forEach(out::println);
        out.println();

        // foreach mit Lambda
        list.forEach(n -> out.printf("Die Zahl ist %d\n", n));
        out.println();
    }

    static void demoRemoveIf() {
        mlog();
        Collection<Integer> list = new ArrayList<>(Arrays.asList(20, 40, 10, 30));
        list.removeIf(elem -> elem >= 30);
        out.println(list);
        out.println();
    }

    static void demoSort() {
        mlog();
        List<Integer> list = Arrays.asList(20, 40, 10, 30);
        // list.sort((i0, i1) -> i0.compareTo(i1));
        list.sort(Comparator.naturalOrder());
        out.println(list);
        out.println();
    }

    static void demoReplaceAll() {
        mlog();
        List<Integer> list = Arrays.asList(20, 40, 10, 30);
        list.replaceAll(elem -> 2 * elem);
        out.println(list);
        out.println();
    }
}
