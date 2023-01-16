package util.functional.proxies;

import java.awt.Color;
import java.util.function.BiFunction;

public class BiFunctionProxy<T0, T1, R> extends Proxy<BiFunction<T0, T1, R>> implements BiFunction<T0, T1, R> {

	private final static Color color = new Color(128, 128, 255);

	public BiFunctionProxy(BiFunction<T0, T1, R> function, AroundAdvice aroundAdvice) {
		super(function, aroundAdvice, color, "BiFunction.apply");
	}

	@Override
	public R apply(T0 arg0, T1 arg1) {
		this.before(arg0, arg1);
		R result = this.toInvoke.apply(arg0, arg1);
		this.after(result);
		return result;
	}
}
