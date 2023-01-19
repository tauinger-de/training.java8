package appl;

import book.Book;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;
import static util.Util.mlog;

@SuppressWarnings("ComparatorCombinators")
public class Application {

    static final Book book1 = new Book("1111", "Pascal", 10);
    static final Book book2 = new Book("3333", "Modula", 30);
    static final Book book3 = new Book("5555", "Pascal", 30);

    public static void main(String[] args) {
        demoCompareToWithIsbn();
        demoComparingWithIsbn();
        demoComparingWithIsbnMethodReference();

        demoComparatorPrice();
        demoComparingWithPrice();
        demoComparingInt();
        demoReversed();
        demoThenComparing1();
        demoThenComparing2();
        demoNaturalOrderReversedOrder();
        demoNullsFirst();
        demoSortNullsFirst();
        demoSortNullsLast();
    }

    /**
     * Vergleich nach ISBN mit einem Lambda, welches die ISBNs sich holt und diese vergleicht
     */
    @SuppressWarnings("ComparatorCombinators")
    static void demoCompareToWithIsbn() {
        mlog();
        Comparator<Book> c = (b1, b2) -> b1.getIsbn().compareTo(b2.getIsbn());
        out.println(c.compare(book1, book2)); // -> -2
    }

    /**
     * Vergleich nach ISBN mit einem Key-Extracing Lambda
     */
    @SuppressWarnings("Convert2MethodRef")
    static void demoComparingWithIsbn() {
        mlog();
        Comparator<Book> c = Comparator.comparing(b -> b.getIsbn());
        out.println(c.compare(book1, book2)); // -> -2
    }

    /**
     * Vergleich nach ISBN mit einem Key-Extracing Lambda -- nun mit Methoden-Ref
     */
    static void demoComparingWithIsbnMethodReference() {
        mlog();
        Comparator<Book> c = Comparator.comparing(Book::getIsbn);
        out.println(c.compare(book1, book2)); // -> -2
    }

    /**
     * Vergleich nach Preis -- auskommentiert ist die ausführliche Form
     */
    static void demoComparatorPrice() {
        mlog();
        Comparator<Book> c = (b1, b2) -> b1.getPriceInCent() - b2.getPriceInCent();
//			if (b1.getPrice() > b2.getPrice())
//				return 1;
//			if (b1.getPrice() < b2.getPrice())
//				return -1;
//			return 0;
        out.println(c.compare(book1, book2)); // -> -1
    }

    /**
     * Vergleich nach Preis -- wieder mit Key-Extractor
     */
    static void demoComparingWithPrice() {
        mlog();
        Comparator<Book> c = Comparator.comparing(Book::getPrice);
        out.println(c.compare(book1, book2)); // -> -1
    }

    /**
     * Vergleich nach Preis -- diesmal aber auf int Basis (`price` ist double)
     */
    static void demoComparingInt() {
        mlog();
        Comparator<Book> c = Comparator.comparingInt(Book::getPriceInCent);
        out.println(c.compare(book1, book2)); // -> -1
    }

    static void demoReversed() {
        mlog();
        Comparator<Book> c = Comparator.comparingInt(Book::getPriceInCent).reversed();
        out.println(c.compare(book1, book2)); // -> 1
    }

    static void demoThenComparing1() {
        mlog();
        Comparator<Book> c = Comparator.comparing(Book::getTitle);
        c = c.thenComparingInt(Book::getPriceInCent);
        out.println(c.compare(book1, book3)); // -> -1
    }

    static void demoThenComparing2() {
        mlog();
        Comparator<Book> c = Comparator
                .comparing(Book::getTitle)
                .thenComparingInt(Book::getPriceInCent);
        out.println(c.compare(book1, book3)); // -> -4
    }

    /**
     * Natural order bedeutet Aufruf von {@link Comparable#compareTo(Object)}
     */
    static void demoNaturalOrderReversedOrder() {
        mlog();
        Comparator<Integer> c1 = Comparator.naturalOrder();
        out.println(c1.compare(20, 30)); // -1
        Comparator<Integer> c2 = Comparator.reverseOrder();
        out.println(c2.compare(20, 30)); // 1
    }

    static void demoNullsFirst() {
        mlog();
        Comparator<Integer> c = Comparator.naturalOrder();
        c = Comparator.nullsFirst(c);
        out.println(c.compare(20, 30)); // -1
        out.println(c.compare(null, 30)); // -1
        out.println(c.compare(30, null)); // 1
    }

    static void demoSortNullsFirst() {
        mlog();
        List<Integer> list = Arrays.asList(10, null, 30, null, 20);
        Comparator<Integer> c = Comparator.nullsFirst(Comparator.naturalOrder());
        list.sort(c);
        out.println(list);
    }

    static void demoSortNullsLast() {
        mlog();
        List<Integer> list = Arrays.asList(10, null, 30, null, 20);
        Comparator<Integer> c = Comparator.nullsLast(Comparator.reverseOrder());
        list.sort(c);
        out.println(list);
    }

}
