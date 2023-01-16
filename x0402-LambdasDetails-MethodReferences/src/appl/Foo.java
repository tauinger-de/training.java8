package appl;

public class Foo {

	/**
	 * Methode ohne Parameter und ohne Return-Wert
	 */
    public static void doSomething() {
        System.out.println("f()");
    }

	/**
	 * Methode mit einem Parameter und Return-Wert gleichen Typs
	 */
    public static int doubleInput(int v) {
        return v * 2;
    }

	/**
	 * Methode mit zwei Parametern und Return-Wert gleichen Typs
	 */
    public static int multiplyInput(int v, int w) {
        return v * w;
    }

}
