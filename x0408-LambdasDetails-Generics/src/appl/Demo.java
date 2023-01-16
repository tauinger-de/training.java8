package appl;

import static java.lang.System.out;

import java.io.Serializable;
import java.lang.reflect.Method;

import util.LambdaUtil;

@FunctionalInterface
interface Consumer<T> extends Serializable {
	public abstract void consume(T value);
}

public class Demo {
	public static void main(String[] args) {
		
		final Object[] array = { 
				3.14, 10, "Hello", 20, 2.71, "World" };
		
		final Consumer<String> stringConsumer = (v) -> out.println("\t" + v);
		final Consumer<Integer> intConsumer = (v) -> out.println("\t" + v);
		final Consumer<Double> doubleConsumer = (v) -> out.println("\t" + v);
		final Consumer<Number> numberConsumer = (v) -> out.println("\t" + v);
		
		consume("all Strings", array, stringConsumer);
		consume("all Ints", array, intConsumer);
		consume("all Doubles", array, doubleConsumer);
		consume("all Numbers", array, numberConsumer);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void consume(String info, Object[] array, Consumer<?> consumer) {
		out.println(info);
		final Method m = LambdaUtil.getMethod(consumer);
		final Class<?> parameterType = m.getParameterTypes()[0];
		for (Object value : array) {
			if (parameterType.isAssignableFrom(value.getClass())) {
				((Consumer)consumer).consume(value);
			}
		}
	}
}
