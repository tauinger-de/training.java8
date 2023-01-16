package ex1;

class Foo {
    public final int i;
    public final String s;
    public final String data;

    public Foo(int i, String s, String data) {
        this.i = i;
        this.s = s;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Foo [i=" + i + ", s=" + s + ", data=" + data + "]";
    }
}
