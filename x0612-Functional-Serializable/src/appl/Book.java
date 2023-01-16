package appl;

import java.io.Serializable;

public class Book implements Serializable {

    public final String isbn;
    public final String title;
    public final String author;
    public final int year;

    public Book(String isbn, String title, String author, int year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book [" + isbn + ", " + title + ", " + author + ", " + year + "]";
    }
}
