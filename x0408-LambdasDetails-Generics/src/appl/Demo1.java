package appl;

import static java.lang.System.out;

public class Demo1 {
	public static void main(String[] args) {
		
		final Object[] array = { 
				3.14, 10, "Hello", 20, 2.71, "World" };
		
		final Consumer<String> stringConsumer = (v) -> out.println("\t" + v);
		final Consumer<Integer> intConsumer = (v) -> out.println("\t" + v);
		final Consumer<Double> doubleConsumer = (v) -> out.println("\t" + v);
		final Consumer<Number> numberConsumer = (v) -> out.println("\t" + v);
		
		consume("all Strings", array, String.class, stringConsumer);
		consume("all Ints", array, Integer.class, intConsumer);
		consume("all Doubles", array, Double.class, doubleConsumer);
		consume("all Numbers", array, Number.class, numberConsumer);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T >void consume(String info, Object[] array, Class<T> cls, Consumer<T> consumer) {
		out.println(info);
		for (Object value : array) {
			if (cls.isAssignableFrom(value.getClass())) {
				((Consumer)consumer).consume(value);
			}
		}
	}
}
