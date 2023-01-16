package appl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
        demoJoining();
        demoStringJoin();
        demoJoiningInts();
        demoCounting();
        demoGroupingBy();
        demoGroupingByCounting();
        demoPartitioningBy();
        demoGroupingByFoo1();
        demoGroupingByFoo2();
        demoGroupingByABC();
    }

    static void demoJoining() {
        mlog();
        List<String> list = Arrays.asList("red", "green", "blue");
        String s = list.stream().collect(Collectors.joining(", "));
        System.out.println(s);
    }

    static void demoStringJoin() {
        mlog();
        List<String> list = Arrays.asList("red", "green", "blue");
        String s = String.join(", ", list);
        System.out.println(s);
    }

    static void demoJoiningInts() {
        mlog();
        List<Integer> list = Arrays.asList(10, 20, 30);
        String s = list.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(", "));
        System.out.println(s);
    }

    static void demoCounting() {
        mlog();
        List<String> list = Arrays.asList("a", "bb", "ccc", "ddd", "ee", "f");
        long count = list.stream().collect(Collectors.counting());
        System.out.println(count);
    }

    static void demoGroupingBy() {
        mlog();
        List<String> list = Arrays.asList("a", "bb", "ccc", "ddd", "ee", "f");
        Map<Integer, List<String>> map = list.stream().collect(Collectors.groupingBy(s -> s.length()));
        System.out.println(map);
    }

    static void demoGroupingByCounting() {
        mlog();
        List<String> list = Arrays.asList("a", "bb", "ccc", "ddd", "ee", "f");
        Map<Object, Long> map = list.stream().collect(Collectors.groupingBy(s -> s.length(), Collectors.counting()));
        System.out.println(map);
    }

    static void demoPartitioningBy() {
        mlog();
        List<String> list = Arrays.asList("a", "bb", "ccc", "ddd", "ee", "f");
        Map<Boolean, List<String>> map = list.stream().collect(Collectors.partitioningBy(s -> s.length() > 2));
        System.out.println(map);
    }

    static void demoGroupingByFoo1() {
        mlog();
        Map<Integer, List<Foo>> map = Foo.foos.stream()
                .collect(
                        Collectors.groupingBy(f -> f.key1));
        map.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(f -> {
                System.out.println("\t" + f);
            });
        });
    }

    static void demoGroupingByFoo2() {
        mlog();
        Map<Integer, Map<Integer, List<Foo>>> map = Foo.foos.stream()
                .collect(
                        Collectors.groupingBy(f1 -> f1.key1,
                                Collectors.groupingBy(f2 -> f2.key2)));
        map.forEach((k1, v1) -> {
            System.out.println(k1);
            v1.forEach((k2, v2) -> {
                System.out.println("\t" + k2);
                v2.forEach(f -> {
                    System.out.println("\t\t" + f);
                });
            });
        });
    }

    static void demoGroupingByABC() {
        mlog();
        final ABC[] abcRecords = new ABC[]{
                new ABC(1, "A1", 1, "B11", 1, "C111"),
                new ABC(1, "A1", 1, "B11", 2, "C112"),
                new ABC(1, "A1", 2, "B12", 3, "C121"),
                new ABC(1, "A1", 2, "B12", 4, "C122"),
                new ABC(2, "A2", 1, "B21", 1, "C211"),
                new ABC(2, "A2", 1, "B21", 2, "C212"),
                new ABC(2, "A2", 2, "B22", 3, "C221"),
                new ABC(2, "A2", 2, "B22", 4, "C222"),
        };

        Map<A, Map<B, List<C>>> map = Arrays.stream(abcRecords)
                .collect(
                        Collectors.groupingBy(abc -> new A(abc.aId, abc.aName),
                                Collectors.groupingBy(abc -> new B(abc.bId, abc.bName),
                                        Collectors.mapping(abc -> new C(abc.cId, abc.cName),
                                                Collectors.toList())))

                );

        map.forEach((a, bList) -> {
            System.out.println(a);
            bList.forEach((b, cList) -> {
                System.out.println("\t" + b);
                cList.forEach(c -> {
                    System.out.println("\t\t" + c);
                });

            });
        });
    }
}
