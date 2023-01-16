package appl;

public interface Foo {

    default void g() {
        System.out.println("g");
    }

}