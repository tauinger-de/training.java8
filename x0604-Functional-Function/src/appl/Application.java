package appl;

import java.util.function.*;

import static util.Util.mlog;

public class Application {
    public static void main(String[] args) {
        demoFunction();
        demoSupplierFunctionConsumer();
        demoSupplierFunctionConsumerPiped();
        demoSupplierFunctionConsumerCToB();
        demoAndThen();
        demoCompose();
        demoAndThenCompose();
        demoIdentity();
        demoIntFunctionInteger();
        demoIntFunctionDouble();
        demoBiFunction();
        demoBiFunctionABC();
    }

    /**
     * Eine einfache parsing-Funktion.
     */
    @SuppressWarnings("Convert2MethodRef")
    static void demoFunction() {
        mlog();
        Function<String, Integer> f = v -> Integer.parseInt(v);
        int v = f.apply("42");
        System.out.println(v);
    }

    /**
     * Kombination von Supplier, Function und Consumer -- diesmal auch mit Methoden-Referenz im
     * Lambda
     */
    static void demoSupplierFunctionConsumer() {
        mlog();
        Supplier<String> supplier = () -> "42";
        Function<String, Integer> function = Integer::parseInt;
        Consumer<Integer> consumer = System.out::println;
        consumer.accept(function.apply(supplier.get()));
    }

    /**
     * Gleiche wie oben, nur mit Hilfsfunktion `pipe()` zur Ausführung gebracht.
     */
    static void demoSupplierFunctionConsumerPiped() {
        mlog();
        Supplier<String> supplier = () -> "42";
        Function<String, Integer> function = Integer::parseInt;
        Consumer<Integer> consumer = System.out::println;
        pipe(supplier, function, consumer);
    }

    /**
     * Hier wird ein Objekt vom Typ C auf B transformiert und von einem Consumer ausgegeben, der nur
     * A versteht. Geht auch!
     */
    static void demoSupplierFunctionConsumerCToB() {
        mlog();
        Supplier<C> supplier = () -> new C(1, 2, 3);
        Function<C, B> function = (c) -> new B(c.x + 1, c.y + 1);
        Consumer<A> consumer = a -> System.out.println(a.x);
        pipe(supplier, function, consumer);
    }

    /**
     * Verkettung von Funktionen über andThen()
     */
    static void demoAndThen() {
        mlog();
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f2 = x -> 2 * x;
        Function<Integer, Integer> f3 = x -> x * x;
        int v = f1.andThen(f2).andThen(f3).apply(3);
        System.out.println(v); // -> 64
    }

    /**
     * `compose()` verkettet von hinten nach vorne
     */
    static void demoCompose() {
        mlog();
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f2 = x -> 2 * x;
        Function<Integer, Integer> f3 = x -> x * x;
        int v = f1.compose(f2).compose(f3).apply(3);
        System.out.println(v); // -> 19
    }

    /**
     * Schwieriger zu lesen wird es, wenn beides gemischt wird. Warum wird hier 20 ausgegeben?
     */
    static void demoAndThenCompose() {
        mlog();
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f2 = x -> 2 * x;
        Function<Integer, Integer> f3 = x -> x * x;
        int v = f1.andThen(f2).compose(f3).apply(3);
        System.out.println(v); // -> 20
    }

    static void demoIdentity() {
        mlog();
        Function<Integer, Integer> f = Function.identity();
        int v = f.apply(42);
        System.out.println(v); // -> 42
    }

    static void demoIntFunctionInteger() {
        mlog();
        IntFunction<Integer> f = x -> 2 * x;
        int v = f.apply(42);
        System.out.println(v); // -> 84
    }

    static void demoIntFunctionDouble() {
        mlog();
        IntFunction<Double> f = x -> Math.sqrt(x);
        double v = f.apply(2);
        System.out.println(v); // -> 1.41...
    }

    static void demoBiFunction() {
        mlog();
        BiFunction<Integer, Integer, Double> f = (x, y) -> Math.sqrt(x * x + y * y);
        double d = f.apply(3, 4);
        System.out.println(d);
    }

    static void demoBiFunctionABC() {
        mlog();
        BiFunction<A, B, C> f = (a, b) -> new C(a.x, b.x, b.y);
        C c = f.apply(new A(1), new B(2, 3));
        System.out.println(c.x + " " + c.y + " " + c.z);
    }

    /**
     * Hilfsmethode, welche die übergebenen Lambdas ausführt.
     * <p>
     * Hier sieht man auch schön das PECS Prinzip: Producer-Extends, Consumer-Super
     */
    static <S, T> void pipe(
            Supplier<? extends S> s,
            Function<S, T> f,
            Consumer<? super T> c) {
        c.accept(f.apply(s.get()));
    }
}