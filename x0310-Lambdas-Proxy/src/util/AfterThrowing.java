package util;

import java.lang.reflect.Method;

public interface AfterThrowing {
	abstract public void after(Method m, Object[] args, Throwable t);
}
