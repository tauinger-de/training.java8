package util;

import java.lang.reflect.Method;

public interface AfterReturning {
    abstract public void after(Method m, Object[] args, Object result);
}
