package appl;

public interface Foo {

    default void f() {
        System.out.println("Foo.f");
    }

}
