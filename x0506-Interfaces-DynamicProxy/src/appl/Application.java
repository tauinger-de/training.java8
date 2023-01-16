package appl;

import java.lang.reflect.Proxy;

import static java.lang.System.out;


public class Application {
    public static void main(String[] args) {
        final MathService m1 = new MathServiceImpl();
        out.println(m1.sum(40, 2));
        out.println(m1.diff(80, 3));
        out.println();

        final MathService m2 = (MathService) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class<?>[]{MathService.class},
                new TraceHandler(m1));
        out.println(m2.sum(40, 2));
        out.println(m2.diff(80, 3));
        out.println();
    }
}
