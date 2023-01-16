package appl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TraceHandler implements InvocationHandler {
    final Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // quick and dirty...
        System.out.println("--> " + method.getName() + " " + Arrays.toString(args));
        final Object result = method.invoke(this.target, args);
        System.out.println("<-- " + method.getName() + " --> " + result);
        return result;
    }

};
