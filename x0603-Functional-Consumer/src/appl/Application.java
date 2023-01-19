package appl;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

import static util.Util.mlog;


@SuppressWarnings("unused")
public class Application {

    static Consumer<String> f(int magic) {
        String world = "World";  // effective final
        return (String s) -> {
            System.out.println(s + world + " " + magic);
        };
    }

    public static void main(String[] args) {
        Consumer<String> c = f(77);
        c.accept("Hello");

//		demoConsumer();
//		demoSupplierConsumer();
//		demoPipe();
//		demoPipeCC();
//		demoPipeCA();
//		demoPipeCB();
//		demoPipeBA();
//		demoPipeBC();
//		demoAndThen();
        demoAndThenCBA();
//		demoListForEach();
//		demoIntConsumer();
        demoBiConsumer();
    }

    /**
     * ein Consumer als anonyme Klasse
     */
    static void demoConsumer1() {
        mlog();
        Consumer<Integer> c = new Consumer<Integer>() {
            public void accept(Integer v) {
                System.out.println(v);
            }
        };
        c.accept(42);
    }

    /**
     * ein Consumer als Lambda
     */
    @SuppressWarnings("Convert2MethodRef")
    static void demoConsumer() {
        mlog();

        // lambda "normal"
        Consumer<Integer> c = v -> System.out.println(v);
        c.accept(42);

        // lambda mit Methoden-Referenz -- macht genau das Gleiche wie oben
        Consumer<Integer> c2 = System.out::println;
        c2.accept(42);
    }

    /**
     * Kombiniert einen Supplier mit einem Consumer
     */
    static void demoSupplierConsumer() {
        mlog();
        Supplier<Integer> supplier = () -> 42;
        Consumer<Integer> consumer = System.out::println;
        consumer.accept(supplier.get());
    }

    /**
     * Gleiche Kombination wie oben, allerdings erfolgt die Ausführung über die Hilfsmethode
     * `pipe()`
     */
    static void demoPipe() {
        mlog();
        Supplier<Integer> supplier = () -> 42;
        Consumer<Integer> consumer = System.out::println;
        pipe(supplier, consumer);
    }

    /**
     * Hier werden Inhalte aus dem Kapitel Kovarianz/Kontravarianz aufgegriffen, konkret, dass ein
     * Supplier und Consumer vom gleichen generischen Typ "C" miteinander arbeiten können.
     */
    static void demoPipeCC() {
        mlog();
        Supplier<C> supplier = () -> new C(1, 2, 3);
        Consumer<C> consumer = c -> System.out.println(c.x + c.y + c.z);
        pipe(supplier, consumer);
    }

    /**
     * Analog oben, aber jetzt ist der Supplier "spezifischer", da er ein C liefert. Und C kann
     * problemlos auf A up-ge-castet werden.
     */
    static void demoPipeCA() {
        mlog();
        Supplier<C> supplier = () -> new C(1, 2, 3);
        Consumer<A> consumer = a -> System.out.println(a.x);
        pipe(supplier, consumer);
    }

    /**
     * Analog oben, nun aber problematisch: Der Supplier ist sehr allgemein und liefert "nur" ein A.
     * Der Consumer möchte aber mindestens ein C haben. Das passt dann nicht zusammen -- `pipe()`
     * kann nicht ausgeführt werden.
     */
    static void demoPipeAC() {
        mlog();
        Supplier<A> supplier = () -> new A(1);
        Consumer<C> consumer = a -> System.out.println(a.x);
//        pipe(supplier, consumer);
    }

    static void demoAndThen() {
        mlog();
        Consumer<Integer> c1 = v -> System.out.println("c1: " + v);
        Consumer<Integer> c2 = v -> System.out.println("c2: " + v);
        Consumer<Integer> c3 = v -> System.out.println("c3: " + v);
        Consumer<Integer> c4 = c1.andThen(c2);
        Consumer<Integer> c5 = c4.andThen(c3);
        c5.accept(42);
        c1.andThen(c2).andThen(c3).accept(42);
    }

    static void demoAndThenCBA() {
        mlog();
        Consumer<C> c1 = c -> System.out.println("c1: " + (c.x + c.y + c.z));
        Consumer<B> c2 = b -> System.out.println("c2: " + (b.x + b.y));
        Consumer<A> c3 = a -> System.out.println("c3: " + (a.x));
        Consumer<C> x = c1.andThen(c2);
        x.accept(new C(1, 2, 3));

        Consumer<C> y = x.andThen(c3);
        y.accept(new C(1, 2, 3));
    }

    static void demoListForEach() {
        mlog();
        List<Integer> list = Arrays.asList(10, 20, 30);
        list.forEach(System.out::println);
    }

    static void demoIntConsumer() {
        mlog();
        IntConsumer c = System.out::println;
        c.accept(42);
    }

    static void demoBiConsumer() {
        mlog();
        BiConsumer<Integer, String> biConsumer = (index, name) -> System.out.printf("#%d: %s\n", index, name);

        String[] niceCities = new String[]{"Wien", "Hamburg", "Porto"};
        for (int n = 0; n < niceCities.length; n++) {
            biConsumer.accept(n + 1, niceCities[n]);
        }
    }

    static <T> void pipe(Supplier<? extends T> s, Consumer<? super T> c) {
        c.accept(s.get());
    }
}

