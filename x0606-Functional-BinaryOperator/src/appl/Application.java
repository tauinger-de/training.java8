package appl;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;

import static util.Util.mlog;

public class Application {
    public static void main(String[] args) {
        demoBinaryOperator();
        demoMinByMaxBy();
        demoIntBinaryOperator();
        demoDoubleBinaryOperator();
    }

    static void demoBinaryOperator() {
        mlog();
        BinaryOperator<Integer> op = (x, y) -> x + y;
        System.out.println(op.apply(40, 2)); // -> 42
    }

    static void demoMinByMaxBy() {
        mlog();
        BinaryOperator<String> min = BinaryOperator.minBy((x, y) -> x.compareTo(y));
        BinaryOperator<String> max = BinaryOperator.maxBy((x, y) -> x.compareTo(y));
        System.out.println(min.apply("Hello", "World")); // -> "Hello"
        System.out.println(max.apply("Hello", "World")); // -> "World"
    }

    static void demoIntBinaryOperator() {
        mlog();
        IntBinaryOperator op = (x, y) -> x + y;
        System.out.println(op.applyAsInt(40, 2)); // 42
    }

    static void demoDoubleBinaryOperator() {
        mlog();
        DoubleBinaryOperator op = (x, y) -> x + y;
        System.out.println(op.applyAsDouble(40, 2)); // -> 42.0
    }

}
