package appl;

import static util.Util.mlog;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;


@SuppressWarnings("unused")
public class Application {

	static Consumer<String> f(int magic) {
		String world = "World";  // effective final
		return (String s) -> {
			System.out.println(s + world + " " + magic);
		};
	}

	public static void main(String[] args) {
		Consumer<String>  c = f(77);
		c.accept("Hello");
		
//		Konsument c1 = v -> System.out.println("c1 = " + v);
//		Konsument c2 = v -> System.out.println("c2 = " + v);
//		Konsument c3 = v -> System.out.println("c3 = " + v);
//		Konsument c4 = c1.andThen(c2).andThen(c3);
//		c4.accept(77);
//		
		
//		demoConsumer();
//		demoSupplierConsumer();
//		demoPipe();
//		demoPipeCC();
//		demoPipeCA();
//		demoPipeCB();
//		demoPipeBA();
//		demoPipeBC();
//		demoAndThen();
//		demoAndThenCBA();
//		demoListForEach();
//		demoIntConsumer();
	}

	static void demoConsumer1() {
		mlog();
		Consumer<Integer> c = new Consumer<Integer>() {
			public void accept(Integer v) {
				System.out.println(v);
			}
		};
		c.accept(42);
	}

	static void demoConsumer() {
		mlog();
		Consumer<Integer> c = v -> System.out.println(v);
		c.accept(42);
	}

	static void demoSupplierConsumer() {
		mlog();
		Supplier<Integer> supplier = () -> 42;
		Consumer<Integer> consumer = v -> System.out.println(v);
		consumer.accept(supplier.get());
	}

	static void demoPipe() {
		mlog();
		Supplier<Integer> supplier = () -> 42;
		Consumer<Integer> consumer = v -> System.out.println(v);
		pipe(supplier, consumer);
	}

	static void demoPipeCC() {
		mlog();
		Supplier<C> supplier = () -> new C(1, 2, 3);
		Consumer<C> consumer = c -> System.out.println(c.x + c.y + c.z);
		pipe(supplier, consumer);
	}

	static void demoPipeCA() {
		mlog();
		Supplier<C> supplier = () -> new C(1, 2, 3);
		Consumer<A> consumer = a -> System.out.println(a.x);
		pipe(supplier, consumer);
	}

	static void demoPipeAC() {
		mlog();
		Supplier<A> supplier = () -> new A(1);
		Consumer<C> consumer = a -> System.out.println(a.x);
		//pipe(supplier, consumer);
	}

	static void demoPipeCB() {
		mlog();
		Supplier<C> supplier = () -> new C(1, 2, 3);
		Consumer<B> consumer = b -> System.out.println(b.x + b.y);
		pipe(supplier, consumer);
	}

	static void demoPipeBA() {
		mlog();
		Supplier<B> supplier = () -> new B(1, 2);
		Consumer<A> consumer = a -> System.out.println(a.x);
		pipe(supplier, consumer);
	}

	static void demoPipeBC() {
		mlog();
		Supplier<B> supplier = () -> new B(1, 2);
		Consumer<C> consumer = c -> System.out.println(c.x + c.y + c.z);
		// pipe(supplier, consumer); // illegal
	}

	static void demoAndThen() {
		mlog();
		Consumer<Integer> c1 = v -> System.out.println("c1: " + v);
		Consumer<Integer> c2 = v -> System.out.println("c2: " + v);
		Consumer<Integer> c3 = v -> System.out.println("c3: " + v);
		Consumer<Integer> c4 = c1.andThen(c2);
		Consumer<Integer> c5 = c4.andThen(c3);
		c5.accept(42);
		c1.andThen(c2).andThen(c3).accept(42);
	}

	static void demoAndThenCBA() {
		mlog();
		Consumer<C> c1 = c -> System.out.println("c1: " + (c.x + c.y + c.z));
		Consumer<B> c2 = b -> System.out.println("c2: " + (b.x + b.y));
		Consumer<A> c3 = a -> System.out.println("c3: " + (a.x));
		Consumer<C> x = c1.andThen(c2);
		x.accept(new C(1, 2, 3));
		
		Consumer<C> y = x.andThen(c3);
		y.accept(new C(1, 2, 3));
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(x);
		System.out.println(y);
	}

	static void demoListForEach() {
		mlog();
		List<Integer> list = Arrays.asList(10, 20, 30);
		list.forEach(element -> System.out.println(element));
	}

	static void demoIntConsumer() {
		mlog();
		IntConsumer c = x -> System.out.println(x);
		c.accept(42);
	}

	static void demoBiConsumer() {
		mlog();
		BiConsumer<Integer, String> c = (i, s) -> System.out.println(i + " " + s);
		c.accept(42, "Hello");
	}

	static <T> void pipe(Supplier<? extends T> s, Consumer<? super T> c) {
		c.accept(s.get());
	}
}

