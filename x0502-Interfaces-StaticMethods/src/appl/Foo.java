package appl;

public interface Foo {
    public static final int x = 42;

    static void printX() {
        System.out.println(x);
    }

    void f();
}
