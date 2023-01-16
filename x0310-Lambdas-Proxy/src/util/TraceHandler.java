package util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TraceHandler implements InvocationHandler {

    private final Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(">> " + method.getName() + " " + Arrays.toString(args));
        Object result;
        try {
            if (this.target instanceof InvocationHandler)
                result = ((InvocationHandler) this.target).invoke(proxy, method, args);
            else
                result = method.invoke(this.target, args);
            System.out.println("<< " + method.getName() + " " + Arrays.toString(args) + " -> " + result);
            return result;
        } catch (InvocationTargetException e) {
            System.out.println("<< Exception " + method.getName() + " " + Arrays.toString(args) + " -> " + e.getCause());
            throw e.getCause();
        } catch (Throwable e) {
            System.out.println("<< Exception " + method.getName() + " " + Arrays.toString(args) + " -> " + e);
            throw e;
        }
    }
}
