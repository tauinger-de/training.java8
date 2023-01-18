package appl;

import book.Book;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings({"Convert2Lambda", "unused", "Java8ListSort", "ComparatorCombinators", "SameParameterValue"})
public class Application {

    public static void demo0() {
        Comparator<Integer> c = (Integer a, Integer b) -> a - b;
        System.out.println(c.compare(1, 2));    // expected NEG
        System.out.println(c.compare(2, 1));    // expected POS
    }


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
        System.out.println("Alphabetisch:");
        sortAndPrint(
                Book.list,
                (b1, b2) -> b1.getTitle().compareTo(b2.getTitle())
        );

        // Sortierung nach Preis
        System.out.println("\nnach Preis (absteigend):");
        sortAndPrint(
                Book.list,
                (b1, b2) -> b2.getPriceInCent() - b1.getPriceInCent()
        );
    }

    private static void sortAndPrint(List<Book> bookList, Comparator<Book> comparator) {
        bookList.sort(comparator);
        for (Book b : Book.list) {
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
//        demo1();
        demo2();
    }
}
