package util.functional.proxies;

import java.awt.Color;
import java.util.function.Function;

public class FunctionProxy<T, R> extends Proxy<Function<T, R>> implements Function<T, R> {

	private final static Color color = new Color(128, 128, 255);
	
	public FunctionProxy(Function<T, R> function, AroundAdvice aroundAdvice) {
		super(function, aroundAdvice, color, "Function.apply");
	}

	@Override
	public R apply(T arg) {
		this.before(arg);
		R result = this.toInvoke.apply(arg);
		this.after(result);
		return result;
	}
}
