package appl;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "Convert2MethodRef", "Convert2Lambda", "CommentedOutCode"})
public class Application {

    public static void main(String[] args) {
//        demo0();
//		demo1();
//		demo2();
//		demo3();
//        demo4();
        demo5();
    }

    /**
     * nutzt eine anonyme Klasse
     */
    static void demo0() {
        Reader reader = new StringReader("Hello World");
        CharacterProcessor.process(reader, new Handler<Character>() {
            public void handle(Character ch) {
                System.out.print(ch + " ");
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
     * ein Handler, der Zeichen in einer Liste speichert -- WICHTIG, das Lambda nutzt Variablen, die in der
     * Methode klariert sind!
     */
    static void demo2() {
        Reader reader = new StringReader("Hello World");
        List<Character> listOfChars = new ArrayList<>();
        CharacterProcessor.process(reader, (ch) -> listOfChars.add(ch));
        System.out.printf("Die Liste hat %d Elemente\n", listOfChars.size());
        System.out.println(listOfChars);
    }

    /**
     * ein Lambda kann keine äußeren Variablen direkt ändern
     */
    static void demo3Illegal() {
//		Reader reader = new StringReader("Hello World");
//		int numberOfChars = 0;
//		CharacterProcessor.process(reader, (ch) -> numberOfChars++); // compile error "Variable used in lambda expression should be final or effectively final"
//		System.out.println(numberOfChars);
    }

    /**
     * ein Lambda kann aber äußere Variablen benutzen und deren Inhalt ändern (Indirektion). So war es ja auch schon in
     * demo2 mit der Liste an Zeichen
     */
    static void demo3() {
        Reader reader = new StringReader("Hello World");
        Box<Integer> box = new Box<>(0);
        CharacterProcessor.process(reader, (ch) -> box.value++);
        System.out.println(box);
    }

    /**
     * ein Handler mit einer Bedingung (if-Abfrage) -- hier werden nun zwei funktionale Aspekte in einem Lambda
     * implementiert... Nicht ideal.
     */
    static void demo4() {
        Reader reader = new StringReader("Hello World");
        Box<Integer> box = new Box<>(0);
        CharacterProcessor.process(reader, (ch) -> {
            if (!Character.isWhitespace(ch)) {
                box.value++;
            }
        });
        System.out.println(box);
    }

    /**
     * Nutzt zweite process Methode, die auch einen `Tester` als Argument akzeptiert.
     * <p>Wir haben also die Funktionalität in einzelne saubere Bestandteile gesplittet. Java 8 macht uns dies leicht.
     */
    static void demo5() {
        Reader reader = new StringReader("Hello World");
        Box<Integer> box = new Box<>(0);
        CharacterProcessor.process(
                reader,
                ch -> !Character.isWhitespace(ch),
                ch -> box.value++
        );
        System.out.println(box);
    }


    /**
     * Wir können auch eine Bibliothek an Testern definieren.
     */
    static class Testers {
        public static Tester<Character> isWhitespace() {
            return ch -> Character.isWhitespace(ch);
        }

        public static Tester<Character> isUppercase() {
            return ch -> Character.isUpperCase(ch);
        }
    }
}
