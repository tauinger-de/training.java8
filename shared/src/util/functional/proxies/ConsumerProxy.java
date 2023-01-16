package util.functional.proxies;

import java.awt.Color;
import java.util.function.Consumer;

public class ConsumerProxy<T> extends Proxy<Consumer<T>> implements Consumer<T> {

	private final static Color color = new Color(128, 255, 128);
	
	public ConsumerProxy(Consumer<T> consumer, AroundAdvice aroundAdvice) {
		super(consumer, aroundAdvice, color, "Consumer.accept");
	}

	@Override
	public void accept(T arg) {
		this.before(arg);
		this.toInvoke.accept(arg);
		this.after(null);
	}
}
