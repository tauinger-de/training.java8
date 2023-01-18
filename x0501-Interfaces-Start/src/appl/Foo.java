package appl;

// 1.7 compatible
@SuppressWarnings("UnnecessaryModifier")
public interface Foo {

    int x = 42;
    final int y = 43;
    public static final int z = 44;

    void f();

    public void g();

    public abstract void h();

    class C {
    }

    @SuppressWarnings("unused")
    public static class D {
    }

    @SuppressWarnings("unused")
    public static interface E {
    }
}
