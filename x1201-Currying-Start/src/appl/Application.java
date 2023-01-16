// https://dzone.com/articles/whats-wrong-java-8-currying-vs

package appl;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import util.Features;
import util.Util;

public class Application {

	public static void demo1() {
		Util.mlog();
		BiFunction<Integer,Integer,Integer> simplePlus = (a, b) -> a + b;
		Function<Integer, Function<Integer, Integer>> curriedPlus = a -> b -> a + b;

		System.out.println(simplePlus.apply(4, 5));

		System.out.println(curriedPlus.apply(4).apply(5));

		Function<Integer,Integer> plus = curriedPlus.apply(5);
		System.out.println(plus.apply(4));
		System.out.println(plus.apply(6));
	}

	interface F extends Function<Integer, Function<Integer, Function<Integer, Integer>>> {
	}

	private static void demo2() {
		Util.mlog();
		F f = (x) -> (y) -> (z) -> x * y * z;
		int result = f.apply(2).apply(3).apply(4);
		System.out.println(result);

		Function<Integer, Function<Integer, Integer>> r1 = f.apply(2);
		Function<Integer, Integer> r2 = r1.apply(3);
		Integer r3 = r2.apply(4);
		System.out.println(r3);

		int res = ((F) (x) -> (y) -> (z) -> x * y * z).apply(2).apply(3).apply(4);
		System.out.println(res);

		List<Integer> list = Arrays.asList(10, 20, 30);
		int[] sum = { 0 };
		list.forEach(v -> sum[0] += v);
		System.out.println(sum[0]);
	}

	private static void demo3() {
		Util.mlog();
		Function<Integer, Integer> f1 = (x) -> 2 + x;
		System.out.println(f1.apply(40));
		BiFunction<Integer, Integer, Integer> f2 = (x, y) -> x + y;
		System.out.println(f2.apply(40, 2));
		//TriFunction<Integer, Integer, Integer, Integer> f3 = (x, y, z) -> x + x + z;
		//Function<Integer, Function<Integer, Integer>> f3 = x -> y -> x + y;
		//Function<Function<Integer,Integer>, Function<Integer, Integer>> f3 = (x, y) -> 2;
	}
	
	private static void demo4() {
		Util.mlog();
		Function<Integer, Function<Integer, Integer>> plus = a -> (b -> (a + b));
		Features.print(plus.getClass());
		Function<Integer,Integer> f = plus.apply(2);
		Features.print(f.getClass());
		System.out.println(Features.readDeclaredField(f, "arg$1"));
		Function<Integer,Integer> ff = (Function)Features.invokeDeclaredStaticMethod(f.getClass(), "get$Lambda", new Class<?>[]{ Integer.class }, new Object[] { 20 });
		System.out.println(ff.apply(10));
	}
	
	private static void demo5() {
		Util.mlog();
		Func<Integer, Func<Integer, Integer>> plus = a -> (b -> (a + b));
		Func<Integer,Integer> f = plus.apply(2);
		System.out.println(f.apply(40));
		System.out.println(plus.apply(2).apply(40));
		System.out.println(Func.apply(plus, 2, 40));
		System.out.println(plus.apply(2, 40));
	}
	
	private static void demo6() {
		Util.mlog();
		Function<Integer,Integer> f1 = a -> 2 * a;
		Function<Integer,Integer> f2 = b -> b + 1;
		Function<Integer,Integer> c = f1.compose(f2);
		Function<Integer,Integer> a = f1.andThen(f2);
		System.out.println(c.apply(10));
		System.out.println(a.apply(10));
	}
	
	private static void demo7() {
		Util.mlog();
		Function<String, Function<String, Function<String, String>>> greeting = a -> b -> c -> (a + " " + b + c);
		System.out.println(greeting.apply("Hello").apply("Christa").apply("!"));
	}
	
	private static void demo8() {
		Util.mlog();
		String[] names = { "Anton", "Berta", "Caesar" };
		String[] colors = { "Red", "Green", "Blue" };
		BiFunction<String, String, Function<String, Function<String[], String>>> formatter = (a1, a2) -> b -> c -> {
			StringBuilder buf = new StringBuilder();
			buf.append(a1);
			for (int i = 0; i < c.length; i++) {
				if (i > 0)
					buf.append(b);
				buf.append(c[i]);
			}
			buf.append(a2);
			return buf.toString(); 
		};
		
		Function<String[], String> roundBracketsCommaFormatter = formatter.apply("(", ")").apply(", ");
		Function<String[], String> roundBracketsBlankFormatter = formatter.apply("(", ")").apply(" ");
		Function<String[], String> roundBracketsSemicolonFormatter = formatter.apply("(", ")").apply("; ");
		
		System.out.println(roundBracketsCommaFormatter.apply(names));
		System.out.println(roundBracketsBlankFormatter.apply(names));
		System.out.println(roundBracketsSemicolonFormatter.apply(names));


		applyFormatter1(formatter.apply("(", ")"), names); 
		applyFormatter1(formatter.apply("[", "]"), names); 
		applyFormatter2(formatter.apply("(", ")").apply(", "), names); 
		applyFormatter2(formatter.apply("[", "]").apply("; "), names); 
	}
	static class Op {
		public final BinaryOperator<Integer> op;
		public final String name;
		public Op(BinaryOperator<Integer> op, final String name) {
			this.op = op;
			this.name = name;
		}
		public void accept(int a, int b) {
			System.out.println(this.name + "(" + a + ", " + b + ") = " + this.op.apply(a,  b));
		}
	}
	private static void demo9Old() {
		Util.mlog();
		Op op = new Op((a, b) -> a + b, "sum");
		op.accept(40,  2);
	}
	private static void demo9() {
		Util.mlog();
		Function<BinaryOperator<Integer>, Function<String, BiConsumer<Integer, Integer>>> calculator = op -> s -> (a, b) -> {
			System.out.println(s + "(" + a + ", " + b + ") = " + op.apply(a, b));
		};
		calculator.apply((a, b) -> a + b).apply("sum").accept(40, 2);
		Function<String, BiConsumer<Integer, Integer>> c = calculator.apply((a, b) -> a + b);
		c.apply("sum").accept(1, 1);
		c.apply("sum").accept(2, 2);
		BiConsumer<Integer, Integer> cc = calculator.apply((a, b) -> a + b).apply("Summe");
		cc.accept(3, 3);
		cc.accept(4, 5);
		
	}
	private static void applyFormatter1(Function<String, Function<String[], String>> f, String[] values) {
		System.out.println(f.apply(", ").apply(values));
		System.out.println(f.apply(" ").apply(values));
		System.out.println(f.apply("; ").apply(values));
	}

	private static void applyFormatter2(Function<String[], String> f, String[] values) {
		System.out.println(f.apply(values));
		System.out.println(f.apply(values));
		System.out.println(f.apply(values));
	}
	
	private static void demo10() {
		Util.mlog();
		
		BiFunction<Supplier<String>, Consumer<String>, Function<Function<String, Integer>, Integer>> f1 = (before, after) -> x -> {
			String s = before.get();
			Integer i = x.apply(s);
			after.accept(s);
			return i;
		};
		
		Function<Function<String,Integer>, Integer> f2 = f1.apply(
			() -> {
				System.out.println("before");
				return "Hello"; 
			}, 
			(i) -> { 
				System.out.println("after: " + i);
			}
		);
		
		int result = f2.apply((s) -> { 
			System.out.println("action: " + s); 
			return s.length(); 
		});
		
		System.out.println("Final result = " + result);
	}

	interface BeforeActionAfter<T, R> extends BiFunction<Supplier<T>, Consumer<T>, Function<Function<T, R>, R>> {
		public static <T,R> BeforeActionAfter<T, R> get() {
			return (before, after) -> action -> {
				final T t = before.get();
				final R r = action.apply(t);
				after.accept(t);
				return r;
			};
		}
	};
	
	private static void demo11() {
		Util.mlog();
		
		final BeforeActionAfter<String, Integer> beforeAfter = BeforeActionAfter.get();
		
		final Function<Function<String,Integer>, Integer> myBeforeAfter = beforeAfter.apply(
			() -> {
				System.out.println("before");
				return "Hello"; 
			}, 
			(s) -> { 
				System.out.println("after: " + s);
			}
		);
		
		final int result = myBeforeAfter.apply((s) -> { 
			System.out.println("action: " + s); 
			return s.length(); 
		});
		
		System.out.println("Final result = " + result);
	}
	

	public static void main(String[] args) {
		demo1();
		demo2();
		demo3();
		demo4();
		demo5();
		demo6();
		demo7();
		demo8();
		demo9Old();
		demo9();
		demo10();
		demo11();
	}

}
