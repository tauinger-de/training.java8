package appl;

import static util.Query.from;
import static util.Util.mlog;

public class Application0 {

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }

    static void demo1() {
        mlog();
        from(Book.class)
                .select(b -> b.title)
                .where(b -> b.isbn == "2222")
                .log();
    }

    static void demo2() {
        mlog();
        from(Book.class)
                .select(b -> b.isbn, b -> b.price)
                .where(b -> b.isbn == "2222")
                .log();
    }

    static void demo3() {
        mlog();
        from(Book.class)
                .select(b -> b.isbn, b -> b.title, b -> b.price)
                .where(b -> b.price > 40)
                .log();
    }
}
