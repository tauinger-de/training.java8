package appl;

public interface Bar {

    default void f() {
        System.out.println("Bar.f");
    }

}
