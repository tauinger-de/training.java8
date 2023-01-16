package appl;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "Convert2MethodRef", "Convert2Lambda", "Anonymous2MethodRef", "CommentedOutCode"})
public class Application {

    /**
     * nutzt eine anonyme Klasse
     */
    static void demo0() {
        Reader reader = new StringReader("Hello World");
        CharacterProcessor.process(reader, new Handler<Character>() {
            public void handle(Character ch) {
                System.out.print(ch);
            }
        });
    }

    /**
     * nutzt Lambda
     */
    static void demo1() {
        Reader reader = new StringReader("Hello World");
        CharacterProcessor.process(reader, ch -> System.out.println(ch));
    }

    /**
     * ein Handler, der Zeichen in einer Liste speichert
     */
    static void demo2() {
        Reader reader = new StringReader("Hello World");
        List<Character> chars = new ArrayList<>();
        CharacterProcessor.process(reader, (ch) -> chars.add(ch));
        for (Character ch : chars)
            System.out.println(ch);
    }

    /**
     * ein Lambda kann keine äußeren Variablen direkt ändern
     */
    static void demo3Illegal() {
//		Reader reader = new StringReader("Hello World");
//		final int foo = 0;
//		CharacterProcessor.process(reader, (ch) -> foo++);
//		System.out.println(foo);
    }

    /**
     * ein Lambda kann aber äußere Variablen benutzen und deren Inhalt ändern (Indirektion). So war es ja auch schon in
     * demo2 mit der Liste an Zeichen
     */
    static void demo3() {
        Reader reader = new StringReader("Hello World");
        Box<Integer> n = new Box<>(0);
        CharacterProcessor.process(reader, (ch) -> n.value++);
        System.out.println(n);
    }

    /**
     * ein Handler mit einer Bedingung (if-Abfrage) -- hier werden nun zwei funktionale Aspekte in einem Lambda
     * implementiert... Nicht ideal.
     */
    static void demo4() {
        Reader reader = new StringReader("Hello World");
        Box<Integer> n = new Box<>(0);
        CharacterProcessor.process(reader, (ch) -> {
            if (!Character.isWhitespace(ch)) n.value++;
        });
        System.out.println(n);
    }

    /**
     * Nutzt zweite process Methode, die auch einen `Tester` als Argument akzeptiert.
     * <p>Wir haben also die Funktionalität in einzelne saubere Bestandteile gesplittet. Java 8 macht uns dies leicht.
     */
    static void demo5() {
        Reader reader = new StringReader("Hello World");
        Box<Integer> n = new Box<>(0);
        CharacterProcessor.process(reader, ch -> !Character.isWhitespace(ch), ch -> n.value++);
        System.out.println(n);
    }

    public static void main(String[] args) {
        demo0();
//		demo1();
//		demo2();
//		demo3();
//		demo4();
//		demo5();
    }

}
