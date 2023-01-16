package appl;

import static util.Util.mlog;

@SuppressWarnings("unused")
public class Application {

    public static void main(String[] args) {
        demoAssignment();
        demoCast();
        demoParameter();
        demoReturn();
    }

    static void demoAssignment() {
        mlog();
        // Object obj = (x, y)-> x * y;
        Foo foo = (x, y) -> x * y;
        Object obj = foo;
    }

    static void demoCast() {
        mlog();
        Object obj1 = (Foo) (x, y) -> x * y;
        Object obj2 = (Bar) (x, y) -> x * y;
    }

    static void demoParameter() {
        mlog();
        hello((x, y) -> x * y);
    }

    static void demoReturn() {
        mlog();
        Foo foo = world();
        System.out.println(foo.f(10, 32));
    }

    static void hello(Foo foo) {
        System.out.println(foo.f(20, 42));
    }

    static Foo world() {
        return (x, y) -> x * y;
    }
}
