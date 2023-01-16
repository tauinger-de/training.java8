package appl;

public class FooBar3 extends FooBar2 implements Foo, Bar {
    @Override
    public void f() {
        super.f();
        System.out.println("Hello");
    }
}
