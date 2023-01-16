package util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class XProxy {
	@SuppressWarnings("unchecked")
	public static <T> T create(final Class<T> iface, final T target, Before before, AfterReturning after, AfterThrowing afterException) {
		InvocationHandler handler = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (before != null)
					before.before(method, args);
				try {
					Object result = method.invoke(target, args);
					if (after != null)
						after.after(method, args, result);
					return result;
				}
				catch (InvocationTargetException e) {
					if (afterException != null)
						afterException.after(method, args, e.getCause());
					throw e.getCause();
				}
				catch (Throwable e) {
					if (afterException != null)
						afterException.after(method, args, e);
					throw e;
				}
			}
		};
		return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[] { iface }, handler);
	}
}
