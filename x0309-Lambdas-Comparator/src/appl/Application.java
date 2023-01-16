package appl;

import java.util.Collections;
import java.util.Comparator;

import static java.util.Comparator.comparing;

@SuppressWarnings("Convert2Lambda")
public class Application {

    /**
     * Sortierung einer Liste mit einer anonymen Klasse als Comparator.
     * <p>
     * Ausgabe der Bücher mithilfe einer regulären for-Schleife.
     */
    static void demo1() {
        Collections.sort(Book.list, new Comparator<Book>() {
            public int compare(Book b1, Book b2) {
                return b1.getTitle().compareTo(b2.getTitle());
            }
        });
        for (Book b : Book.list) {
            System.out.println(b);
        }
    }

    /**
     * Hier erfolgt die Sortierung und Ausgabe mittels Lambdas.
     */
    static void demo2() {
        // alphabetische Sortierung
        Book.list.sort((b1, b2) -> -b1.getTitle().compareTo(b2.getTitle()));

        // Sortierung nach Preis
        Book.list.sort(comparing(Book::getPrice));

        // Ausgabe mittels Lambda Methoden-Referenz
        Book.list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        demo1();
//        demo2();
    }
}
