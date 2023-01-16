package appl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book {
	public String  isbn;
	public String title;
	public int price;
	public Book(String isbn, String title, int price) {
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Book [" + isbn + " " + title + " " + price + "]";
	}

	private static List<Book> books = new ArrayList<>();
	static {
		books.add(new Book("1111", "Pascal", 10));
		books.add(new Book("2222", "Modula", 20));
		books.add(new Book("3333", "Oberon", 30));
		books.add(new Book("4444", "Eiffel", 40));
		books.add(new Book("5555", "Ceylon", 50));
	}
	public static List<Book> list() {
		return Collections.unmodifiableList(books);
	}
}
