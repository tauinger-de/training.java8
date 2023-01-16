package appl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import static util.Util.mlog;

public class Application {
    public static void main(String[] args) {
        demoUnaryOperator();
        demoIdentity();
        demoListReplaceAll();
    }

    static void demoUnaryOperator() {
        mlog();
        UnaryOperator<Integer> op = x -> -x;
        System.out.println(op.apply(42));
    }

    static void demoIdentity() {
        mlog();
        UnaryOperator<Integer> op = UnaryOperator.identity();
        System.out.println(op.apply(42));
    }

    static void demoListReplaceAll() {
        mlog();
        List<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30));

        list.replaceAll(x -> x * 10);
        list.forEach(x -> System.out.println(x)); // -> 100 200 300

        for (int i = 0; i < list.size(); i++)
            list.set(i, list.get(i) * 10);
        for (Integer value : list)
            System.out.println(value);
    }
}


