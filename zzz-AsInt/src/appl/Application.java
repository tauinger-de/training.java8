package appl;

import java.util.function.*;

import static util.Util.mlog;

class C implements IntSupplier, Supplier<Integer> {

    @Override
    public Integer get() {
        return null;
    }

    @Override
    public int getAsInt() {
        return 0;
    }

}

class D implements IntConsumer, Consumer<Integer> {
    @Override
    public void accept(Integer t) {
    }

    @Override
    public void accept(int value) {
    }

}

class E implements IntSupplier, Supplier<Integer> {

    @Override
    public Integer get() {
        return null;
    }

    @Override
    public int getAsInt() {
        return 0;
    }
}

interface CharSupplier extends IntSupplier {
    public default char getAsChar() {
        return (char) getAsInt();
    }
}

interface CharUnaryOperator extends IntUnaryOperator {
    public default char applyAsChar(char ch) {
        return (char) applyAsInt(ch);
    }
}

public class Application {
    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }

    static void demo1() {
        mlog();
        Function<Integer, Integer> f1 = (x) -> 2 * x;
        IntFunction<Integer> f2 = (x) -> 2 * x;
        f1.apply(20);
        f2.apply(20);

        Consumer<Integer> c1 = (i) -> {
        };
        IntConsumer c2 = (i) -> {
        };
        c1.accept(20);
        c2.accept(20);

        Predicate<Integer> p1 = (i) -> true;
        IntPredicate p2 = (i) -> true;
        p1.test(42);
        p2.test(42);

        Supplier<Integer> s1 = () -> 42;
        IntSupplier s2 = () -> 42;
        s1.get();
        s2.getAsInt();

        UnaryOperator<Integer> uo1 = (x) -> x + x;
        IntUnaryOperator uo2 = (x) -> x + x;
        uo1.apply(20);
        uo2.applyAsInt(20);

        BinaryOperator<Integer> bo1 = (x, y) -> x + y;
        IntBinaryOperator bo2 = (x, y) -> x + y;
        bo1.apply(20, 22);
        bo2.applyAsInt(20, 22);
    }

    static void demo2() {
        mlog();
        CharSupplier s = () -> 65;
        System.out.println(s.getAsChar());
        System.out.println(s.getAsInt());

        CharUnaryOperator uo = (ch) -> ch + 1;
        System.out.println(uo.applyAsChar('A'));
        System.out.println(uo.applyAsInt(42));
    }

    static void demo3() {
        mlog();
        C c = new C();
        Integer i1 = c.get();
        int i2 = c.getAsInt();
        System.out.println(i1);
        System.out.println(i2);

        D d = new D();
        d.accept(43);
        d.accept((Integer) 43);
        d.accept(null);
    }
}
