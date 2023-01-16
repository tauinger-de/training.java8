package appl;


public class Calculator {

    public static int plus(int x, int y) {
        System.out.println("Calculator.plus(" + x + ", " + y + ")");
        return x + y;
    }

    public int minus(int x, int y) {
        System.out.println(this + ".minus(" + x + ", " + y + ")");
        return x - y;
    }

    public int times(int x, int y) {
        System.out.println(this + ".times(" + x + ", " + y + ")");
        return x * y;
    }
}
