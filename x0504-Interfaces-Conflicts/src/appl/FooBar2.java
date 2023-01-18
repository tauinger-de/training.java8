package appl;

public class FooBar2 implements Foo, Bar {

    @Override
    public void f() {
        Foo.super.f();
        Bar.super.f();
    }

}
