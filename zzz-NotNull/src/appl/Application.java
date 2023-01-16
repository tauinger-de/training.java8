package appl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)  // ???
@interface NotNull {
}

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
@interface Immutable {
}


@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME) // ???
@interface Readonly {
}

class Point {
    volatile int a;

    volatile int b;


    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Application {
    public static void main(String[] args) {
        @NotNull Object obj = null;
        System.out.println(obj);
        demo1(null);
        List<@NotNull String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add(null);
    }

    static void demo1(@NotNull Object o) {
        System.out.println(o.hashCode());
    }
}
