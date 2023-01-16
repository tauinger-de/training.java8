package util;

import java.lang.reflect.Method;

public interface Before {
    abstract public void before(Method m, Object[] args);
}
