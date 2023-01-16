package appl;

import static util.Query.from;
import static util.Util.mlog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;

import util.Jdbc;
import util.Query;

public class Application2 {
	
	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		try (Connection con = DriverManager.getConnection("jdbc:derby:../dependencies/derby/data;create=true", "user", "password")) {
			try {
				Jdbc.executeUpdate(con, "drop table book");
			}
			catch(Exception e) {
			}
			Jdbc.executeUpdate(con, "create table book(isbn varchar(13), title varchar(40), price integer)");
			for (Book book : Book.list())
				Jdbc.executeUpdate(con, "insert into book values(?, ?, ?)", book.isbn, book.title, book.price);
			
			demo1(con);
			demo2(con);
			demo3(con);
		}
	}
	
	static void print(Object[] row) {
		System.out.println(Arrays.toString(row));
	}

	static void demo1(Connection con) {
		mlog();
		from(Book.class)
				.select(b -> b.isbn, b -> b.price)
				.where(b -> b.isbn == "2222")
				.log()
				.list(con)
				.forEach(row -> print(row));
	}

	static void demo2(Connection con) {
		mlog();
		from(Book.class)
				.select(b -> b.isbn, b -> b.title, b -> b.price)
				.where(b -> b.price > 40)
				.log()
				.list(con)
				.forEach(row -> print(row));
	}

	static void demo3(Connection con) {
		mlog();
		final Query<Book> query = from(Book.class)
				.select(b -> b.isbn, b -> b.title)
				.where(b -> b.price > 20);
		System.out.println(query);
		query.list(con).forEach(row -> print(row));
	}
}
