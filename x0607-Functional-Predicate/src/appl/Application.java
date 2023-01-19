package appl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static util.Util.mlog;

public class Application {
    public static void main(String[] args) {
        demoPredicate();
        demoAnd();
        demoOr();
        demoListRemoveIf();
        demoIsEqual();
    }

    /**
     * Ein einfacher Test mit Lambda implementiert
     */
    static void demoPredicate() {
        mlog();
        Predicate<Integer> p = v -> v % 2 == 0;
        System.out.println(p.test(3)); // -> false
        System.out.println(p.test(4)); // -> true
    }

    /**
     * Verknüpfung zweiter Prädikate mit AND
     */
    static void demoAnd() {
        mlog();
        Predicate<Integer> p1 = v -> v > 10;
        Predicate<Integer> p2 = v -> v < 20;
        System.out.println(p1.and(p2).test(3)); // -> false
        System.out.println(p1.and(p2).test(30)); // -> false
        System.out.println(p1.and(p2).test(13)); // -> true
    }

    /**
     * Verknüpfung zweiter Prädikate mit OR
     */
    static void demoOr() {
        mlog();
        Predicate<Integer> p1 = v -> v % 2 == 0;
        Predicate<Integer> p2 = v -> v < 20;
        Predicate<Integer> finalPredicate = p1.or(p2);
        System.out.println(finalPredicate.test(3)); // -> true
        System.out.println(finalPredicate.test(21)); // -> false
        System.out.println(finalPredicate.test(30)); // -> true
    }

    static void demoListRemoveIf() {
        mlog();
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        list.removeIf(x -> x % 2 == 0);
        list.forEach(System.out::println); // 1 3 5
    }

    /**
     * Erzeugt ein Prädikat, mit dem andere Objekte auf Gleichheit zu dem Target-Objekt getestet
     * werden können
     */
    static void demoIsEqual() {
        mlog();
        Predicate<String> p = Predicate.isEqual(null);
        System.out.println(p.test(null));
    }
}
