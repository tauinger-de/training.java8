package appl;

import java.io.PrintWriter;
import java.io.StringReader;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
        demo1();
        demo2();
    }

    /**
     * Hier werden die funktionalen Interfaces mit Top-Level Klassen instanziiert.
     */
    static void demo1() {
        mlog();
        String input = "abc\n";
        Supplier<Character> reader = new CharacterReader(new StringReader(input));
        Function<Character, Character> toUpper = new ToUpper();
        Consumer<Character> writer = new CharacterWriter(new PrintWriter(System.out));
        process(reader, toUpper, writer);
    }

    /**
     * Hier werden die funktionalen Interfaces teilweise mit Lambdas instanziiert.
     */
    static void demo2() {
        mlog();
        String input = "abc\n";
        process(
                new CharacterReader(new StringReader(input)),
                ch -> Character.toUpperCase(ch),
                ch -> System.out.print(ch)
        );
    }

    /**
     * Hier werden die funktionalen Bausteine in einer Ausführung zusammengebracht.
     */
    static <T, R> void process(Supplier<T> supplier, Function<T, R> function, Consumer<R> consumer) {
        T t;
        while ((t = supplier.get()) != null) {
            R r = function.apply(t);
            consumer.accept(r);
        }
    }

}
