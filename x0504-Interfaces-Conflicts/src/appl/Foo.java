package appl;

public interface Foo {
    public default void f() {
        System.out.println("Foo.f");
    }
}
