package appl;

public class C extends B {
    public int z;

    public C() {
        this(0, 0, 0);
    }

    public C(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }
}
