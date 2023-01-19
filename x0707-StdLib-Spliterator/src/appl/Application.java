package appl;

import java.util.Spliterator;
import java.util.Spliterators;

import static java.lang.System.out;
import static util.Util.XRunnable.xrun;
import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
        demoTryAdvance();
        demoForEachRemaining();
        demoTryAdvanceAndForEachRemaining();
        demoTrySplit();
        demoTrySplitParallel();
        demoIntSpliterator();
        demoArraySplitAndEstimateSize();
    }

    static void demoTryAdvance() {
        mlog();
        final Integer[] array = new Integer[]{10, 20, 30, 40, 50, 60};
        final Spliterator<Integer> s = Spliterators.spliterator(array, Spliterator.ORDERED);
        while (s.tryAdvance((Integer v) -> out.print(v + " "))) {
        }
        out.println();
    }

    static void demoForEachRemaining() {
        mlog();
        final Integer[] array = new Integer[]{10, 20, 30, 40, 50, 60};
        final Spliterator<Integer> s = Spliterators.spliterator(array, 0);
        s.forEachRemaining((Integer v) -> out.print(v + " "));
        out.println();
    }

    static void demoTryAdvanceAndForEachRemaining() {
        mlog();
        final Integer[] array = new Integer[]{10, 20, 30, 40, 50, 60};
        final Spliterator<Integer> s = Spliterators.spliterator(array, 0);
        out.print("tryAdvance: ");
        s.tryAdvance((Integer v) -> out.print(v + " "));
        out.print("\nforEachRemaining: ");
        s.forEachRemaining((Integer v) -> out.print(v + " "));
        out.println();
    }

    static void demoTrySplit() {
        mlog();
        final Integer[] array = new Integer[]{10, 20, 30, 40, 50, 60};
        final Spliterator<Integer> s1 = Spliterators.spliterator(array, 0);
        final Spliterator<Integer> s2 = s1.trySplit();
        System.out.println(s1);
        System.out.println(s2);
        out.print("forEachRemaining of s1: ");
        s1.forEachRemaining(v -> out.print(v + " "));
        out.print("\nforEachRemaining of s2: ");
        s2.forEachRemaining(v -> out.print(v + " "));
        out.println();
    }

    /**
     * Startet zwei Threads, die parallel an dem Ausgangs-Array arbeiten. Durch entsprechende Sleep Anweisungen
     * erfolgt eine Taktung von 50ms Abstand zwischen den Thread-Schritten.
     */
    static void demoTrySplitParallel() {
        mlog();
        final Integer[] array = new Integer[]{10, 20, 30, 40, 50, 60};
        final Spliterator<Integer> s1 = Spliterators.spliterator(array, 0);
        final Spliterator<Integer> s2 = s1.trySplit();
        final Thread t1 = new Thread(() -> {
            s1.forEachRemaining(v -> {
                xrun(() -> Thread.sleep(100));
                out.print(" s1 = " + v);
            });
        });
        t1.start();
        xrun(() -> Thread.sleep(50));
        final Thread t2 = new Thread(() -> {
            s2.forEachRemaining(v -> {
                xrun(() -> Thread.sleep(100));
                out.print(" s2 = " + v);
            });
        });
        t2.start();
        xrun(t1::join);
        xrun(t2::join);
        out.println();
    }

    /**
     * Es gibt diverse Spezial Interfaces für den Umgang mit primitiven Datentypen, wie z.B. {@link Spliterator.OfInt}
     */
    static void demoIntSpliterator() {
        mlog();
        final Spliterator.OfInt s1 = Spliterators.spliterator(new int[]{10, 20, 30, 40, 50, 60}, 0);
        final Spliterator.OfInt s2 = s1.trySplit();
        System.out.println(s1);
        System.out.println(s2);
        s1.forEachRemaining((int v) -> out.print(v + " "));
        s2.forEachRemaining((Integer v) -> out.print(v + " "));
        out.println();
    }

    static void demoArraySplitAndEstimateSize() {
        mlog();
        final SplitableArray<Integer> array = new SplitableArray<>(new Integer[]{10, 20, 30, 40, 50, 60});
        final Spliterator<Integer> s1 = array.spliterator();
        System.out.println("size of s1: " + s1.estimateSize());

        final Spliterator<Integer> s2 = s1.trySplit();
        System.out.println("size of s1 and s2 after split: " + s1.estimateSize() + " " + s2.estimateSize());
        while (s2.tryAdvance(v -> out.print(v + " ")))
            ;
        while (s1.tryAdvance(v -> out.print(v + " ")))
            ;
        out.println();
    }
}
