package appl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;
import static util.Util.XRunnable.xrun;
import static util.Util.mlog;
import static util.Util.tlog;

public class Application {

    public static void main(String[] args) {
        demoSequential();
        demoParallel();
    }

    static void demoSequential() {
        mlog();
        List<Integer> source = Arrays.asList(10, 11, 12, 13, 14, 15);
        List<Integer> result = new ArrayList<>();
        Stream<Integer> s = source.stream().filter(x -> x % 2 == 0);
        s.forEach(x -> result.add(x));
        result.stream().forEach(x -> out.print(x + " "));
        out.println();
    }

    static void demoParallel() {
        mlog();
        List<Integer> source = Arrays.asList(10, 11, 12, 13, 14, 15);
        List<Integer> result = new ArrayList<Integer>() {
            @Override
            public boolean add(Integer value) {
                xrun(() -> Thread.sleep(100));
                tlog("in add von List");
                synchronized (this) {
                    return super.add(value);
                }
            }
        };
        source.parallelStream().filter(x -> x % 2 == 0).forEach(x -> result.add(x));
        result.stream().forEach(x -> out.print(x + " "));
        out.println();
    }

}
