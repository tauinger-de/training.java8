package appl;

import java.util.Spliterator;
import java.util.Spliterators;

import static java.lang.System.out;
import static util.Util.XRunnable.xrun;
import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
//		demoTryAdvance();
//		demoForEachRemaining();
//		demoTryAdvanceAndForEachRemaining();
//		demoTrySlit();
        demoTrySplitParallel();
//		demoIntSpliterator();
//		
//		demoArraySpliteratorForEachRemaining();
//		demoArraySpliteratorTryAdvance();
//		demoArraySplitAndEstimateSize();
    }

    static void demoTryAdvance() {
        mlog();
        final Integer[] array = new Integer[]{10, 20, 30, 40, 50, 60};
        final Spliterator<Integer> s = Spliterators.spliterator(array, 0);
        while (s.tryAdvance((Integer v) -> out.print(v + " ")))
            ;
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
        s.tryAdvance((Integer v) -> out.print(v + " "));
        s.forEachRemaining((Integer v) -> out.print(v + " "));
        out.println();
    }

    static void demoTrySlit() {
        mlog();
        final Integer[] array = new Integer[]{10, 20, 30, 40, 50, 60};
        final Spliterator<Integer> s1 = Spliterators.spliterator(array, 0);
        final Spliterator<Integer> s2 = s1.trySplit();
        System.out.println(s1);
        System.out.println(s2);
        s1.forEachRemaining(v -> out.print(" s1 = " + v));
        s2.forEachRemaining(v -> out.print(" s2 = " + v));
        out.println();
    }

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
        xrun(() -> t1.join());
        xrun(() -> t2.join());
        out.println();
    }

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

    static void demoArraySpliteratorForEachRemaining() {
        mlog();
        final Array<Integer> array = new Array<>(new Integer[]{10, 20, 30, 40, 50, 60});
        final Spliterator<Integer> s = array.spliterator();
        s.forEachRemaining(v -> out.print(v + " "));
        out.println();
    }

    static void demoArraySpliteratorTryAdvance() {
        mlog();
        final Array<Integer> array = new Array<>(new Integer[]{10, 20, 30, 40, 50, 60});
        final Spliterator<Integer> s = array.spliterator();
        while (s.tryAdvance(v -> out.print(v + " ")))
            ;
        out.println();
    }

    static void demoArraySplitAndEstimateSize() {
        mlog();
        final Array<Integer> array = new Array<>(new Integer[]{10, 20, 30, 40, 50, 60});
        final Spliterator<Integer> s1 = array.spliterator();
        System.out.println("s1   : " + s1.estimateSize());
        final Spliterator<Integer> s2 = s1.trySplit();
        System.out.println("s1,s2: " + s1.estimateSize() + " " + s2.estimateSize());
        while (s2.tryAdvance(v -> out.print(v + " ")))
            ;
        while (s1.tryAdvance(v -> out.print(v + " ")))
            ;
        out.println();
    }
}
