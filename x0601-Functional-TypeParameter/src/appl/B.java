package appl;

public class B extends A {
    public final int y;

    public B() {
        this(0, 0);
    }

    public B(int x, int y) {
        super(x);
        this.y = y;
    }
}
