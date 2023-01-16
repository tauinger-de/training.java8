package util.functional.proxies;

import java.awt.Color;

public class RunnableProxy extends Proxy<Runnable> implements Runnable {

	public RunnableProxy(Runnable Runnable, AroundAdvice aroundAdvice) {
		super(Runnable, aroundAdvice, Color.lightGray, "Runnable.run");
	}

	@Override
	public void run() {
		this.before();
		this.toInvoke.run();
		this.after(null);
	}
}
