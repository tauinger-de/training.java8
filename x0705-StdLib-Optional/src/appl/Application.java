package appl;

import static util.Util.mlog;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Application {

	public static void main(String[] args) {
		
		demoEmpty();
		demoOf1();
		demoOf2();
		demoOfNullable1();
		demoOfNullable2();

		demoGet1();
		demoGet2();
		demoIsPresent1();
		demoIsPresent2();
		demoIfPresent1();
		demoIfPresent2();

		demoOrElse1();
		demoOrElse2();
		demoOrElseGet1();
		demoOrElseGet2();
		demoOrElseThrow1();
		demoOrElseThrow2();
		
		demoMap1();
		demoMap2();
		demoFlatMap1();
		demoFlatMap2();
		demoFilter1();
		demoFilter2();
	}

	static void demoEmpty() {
		mlog();
		Optional<Integer> o = Optional.empty();
		System.out.println(o);
	}

	static void demoOf1() {
		mlog();
		Optional<Integer> o = Optional.of(42);
		System.out.println(o);
	}

	static void demoOf2() {
		mlog();
		try {
			Optional.of(null);
		}
		catch (Exception e) {
			System.out.println("Expected: " + e);
		}
	}

	static void demoOfNullable1() {
		mlog();
		Optional<Integer> o = Optional.ofNullable(42);
		System.out.println(o);
	}

	static void demoOfNullable2() {
		mlog();
		Optional<Integer> o = Optional.ofNullable(null);
		System.out.println(o);
	}

	static void demoGet1() {
		mlog();
		Optional<Integer> o = Optional.of(42);
		Integer v = o.get();
		System.out.println(v);
	}

	static void demoGet2() {
		mlog();
		Optional<Integer> o = Optional.empty();
		try {
			o.get();
		}
		catch (NoSuchElementException e) {
			System.out.println("Expected: " + e.getMessage());
		}
	}

	static void demoIsPresent1() {
		mlog();
		Optional<Integer> o = Optional.of(42);
		if (o.isPresent())
			System.out.println(o.get());
		else
			System.out.println("not present");
	}

	static void demoIsPresent2() {
		mlog();
		Optional<Integer> o = Optional.empty();
		if (o.isPresent())
			System.out.println(o.get());
		else
			System.out.println("not present");
	}

	static void demoIfPresent1() {
		mlog();
		Optional<Integer> o = Optional.of(42);
		o.ifPresent(v -> System.out.println(v));
	}

	static void demoIfPresent2() {
		mlog();
		Optional<Integer> o = Optional.empty();
		o.ifPresent(v -> System.out.println(v));
	}

	static void demoOrElse1() {
		mlog();
		Optional<Integer> o = Optional.of(42);
		Integer v = o.orElse(77);
		System.out.println(v);
	}

	static void demoOrElse2() {
		mlog();
		Optional<Integer> o = Optional.empty();
		Integer v = o.orElse(77);
		System.out.println(v);
	}

	static void demoOrElseGet1() {
		mlog();
		Optional<Integer> o = Optional.of(42);
		Integer v = o.orElseGet(() -> 77);
		System.out.println(v);
	}

	static void demoOrElseGet2() {
		mlog();
		Optional<Integer> o = Optional.empty();
		Integer v = o.orElseGet(() -> 77);
		System.out.println(v);
	}

	static void demoOrElseThrow1() {
		mlog();
		Optional<Integer> o = Optional.of(42);
		try {
			Integer v = o.orElseThrow(() -> new Exception("foo"));
			System.out.println(v);
		}
		catch (Exception e) {
			System.out.println("Unexpected: " + e);
		}
	}

	static void demoOrElseThrow2() {
		mlog();
		Optional<Integer> o = Optional.empty();
		try {
			Integer v = o.orElseThrow(() -> new Exception("foo"));
			System.out.println(v);
		}
		catch (Exception e) {
			System.out.println("Expected: " + e);
		}
	}

	static void demoMap1() {
		mlog();
		Optional<Integer> o1 = Optional.of(42);
		Optional<Integer> o2 = o1.map(x -> 2 * x);
		System.out.println(o2);
	}

	static void demoMap2() {
		mlog();
		Optional<Integer> o1 = Optional.empty();
		Optional<Integer> o2 = o1.map(x -> 2 * x);
		System.out.println(o2);
	}

	static void demoFlatMap1() {
		mlog();
		Optional<Integer> o1 = Optional.of(42);
		Optional<Integer> o2 = o1.flatMap(x -> x > 0 ? Optional.of(x) : Optional.empty());
		System.out.println(o2);
	}

	static void demoFlatMap2() {
		mlog();
		Optional<Integer> o1 = Optional.of(-42);
		Optional<Integer> o2 = o1.flatMap(x -> x > 0 ? Optional.of(x) : Optional.empty());
		System.out.println(o2);
	}

	static void demoFilter1() {
		mlog();
		Optional<Integer> o1 = Optional.of(42);
		Optional<Integer> o2 = o1.filter(x -> x % 2 == 0);
		System.out.println(o2);
	}

	static void demoFilter2() {
		mlog();
		Optional<Integer> o1 = Optional.of(77);
		Optional<Integer> o2 = o1.filter(x -> x % 2 == 0);
		System.out.println(o2);
	}
}
