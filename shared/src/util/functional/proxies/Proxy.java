package util.functional.proxies;

import java.awt.Color;
import java.util.Objects;

public abstract class Proxy<T> {
	
	protected final T toInvoke;
	protected final AroundAdvice aroundAdvice;
	protected final Color color;
	protected final String methodName;
	
	protected Proxy(T toInvoke, AroundAdvice aroundAdvice, Color color, String methodName) {
		this.toInvoke = Objects.requireNonNull(toInvoke);
		this.aroundAdvice = Objects.requireNonNull(aroundAdvice);
		this.color = Objects.requireNonNull(color);
		this.methodName = Objects.requireNonNull(methodName);
		this.aroundAdvice.proxy = this;
	}
	
	protected void before(Object... args) {
		this.aroundAdvice.before(args);
	}
	protected void after(Object result) {
		this.aroundAdvice.after(result);
	}
}
