package appl;

import static java.lang.System.out;

public class Application {
    public static void main(String[] args) {
        demoCallDefault();
        demoYAC();
    }

    private static void demoCallDefault() {
        Foo foo = new FooImpl();
        foo.g();
    }

    private static void demoYAC() {
        YAC<Integer> comparator = new YAC<Integer>() {
            public boolean isEqual(Integer v0, Integer v1) {
                return v0.equals(v1);
            }
            public boolean isGreaterThan(Integer v0, Integer v1) {
                return v0.compareTo(v1) > 0;
            }
        };
        out.println(comparator.isEqual(1, 1));
        out.println(comparator.isGreaterThan(2, 1));
        out.println(comparator.isGreaterOrEqual(1, 1));
        out.println(comparator.isGreaterOrEqual(2, 1));
        out.println(comparator.isLessThan(1, 2));
        out.println(comparator.isLessThanOrEqual(1, 2));
        out.println(comparator.isLessThanOrEqual(1, 1));
    }
}
