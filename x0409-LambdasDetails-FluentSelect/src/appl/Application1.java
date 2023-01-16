package appl;

import util.Query;

import java.util.Arrays;

import static util.Query.from;
import static util.Util.mlog;

public class Application1 {

    static {
        Query.database.add("Book", Book.list());
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }

    static void print(Object[] row) {
        System.out.println(Arrays.toString(row));
    }

    static void demo1() {
        mlog();
        from(Book.class)
                .select(b -> b.isbn, b -> b.price)
                .where(b -> b.isbn == "2222")
                .log()
                .list()
                .forEach(row -> print(row));
    }

    static void demo2() {
        mlog();
        from(Book.class)
                .select(b -> b.isbn, b -> b.title, b -> b.price)
                .where(b -> b.price > 40)
                .log()
                .list()
                .forEach(row -> print(row));
    }

    static void demo3() {
        mlog();
        final Query<Book> query = from(Book.class)
                .select(b -> b.isbn, b -> b.title)
                .where(b -> b.price > 20);
        System.out.println(query);
        query.list().forEach(row -> print(row));
    }
}
