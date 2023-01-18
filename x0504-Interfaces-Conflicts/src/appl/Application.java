package appl;


public class Application {

    public static void main(String[] args) {
        Foo foo = new FooBar1();
        foo.f();

        FooBar2 fooBar2 = new FooBar2();
        fooBar2.f();
    }

}
