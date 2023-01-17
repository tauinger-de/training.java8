package book;

import java.util.ArrayList;
import java.util.List;

public class Book {

    //
    // -- felder --
    //

    private final String isbn;

    private final String title;

    private final String author;

    private final double price;

    //
    // -- constructor --
    //


    public Book(String isbn, String title, double price) {
        this(isbn, title, null, price);
    }

    public Book(String isbn, String title, String author, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    //
    // -- methods --
    //

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + isbn + ", " + title + ", " + author + ", " + price + "]";
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getPriceInCent() {
        return (int) Math.round(this.price * 100);
    }

    //
    // ---- statische Liste an Büchern ----
    //

    public static final List<Book> list = new ArrayList<>();

    static {
        list.add(new Book("1111", "Pascal", "Wirth", 44.44));
        list.add(new Book("2222", "Modula", "Wirth", 33.33));
        list.add(new Book("3333", "Oberon", "Wirth", 22.22));
        list.add(new Book("4444", "Eiffel", "Meyer", 11.11));
    }
}
