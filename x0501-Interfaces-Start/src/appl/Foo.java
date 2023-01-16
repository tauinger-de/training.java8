package appl;

// 1.7 compatible
public interface Foo {
    int x = 42;
    final int y = 43;
    public static final int z = 44;

    void f();

    public void g();

    public abstract void h();

    class C {
    }

    public static class D {
    }

    public static interface E {
    }
}
