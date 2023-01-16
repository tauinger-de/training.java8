package appl;

public class Foo {
	public static void f() {
		System.out.println("f()");
	}
	public static int g(int v) {
		return v * 2;
	}
	public static int g(int v, int w) {
		return v * w;
	}
	
	public void r() {
		System.out.println("r()");
	}
	public int s(int v) {
		return v * 2;
	}
}
