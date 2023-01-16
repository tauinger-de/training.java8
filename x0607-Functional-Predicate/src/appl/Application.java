package appl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static util.Util.mlog;

public class Application {
	public static void main(String[] args) {
		demoPredicate();
		demoAnd();
		demoListRemoveIf();
		demoIsEqual();
	}

	static void demoPredicate() {
		mlog();
		Predicate<Integer> p = v -> v % 2 == 0;
		System.out.println(p.test(3)); // -> false
		System.out.println(p.test(4)); // -> true
	}

	static void demoAnd() {
		mlog();
		Predicate<Integer> p1 = v -> v > 10;
		Predicate<Integer> p2 = v -> v < 20;
		System.out.println(p1.and(p2).test(3)); // -> false
		System.out.println(p1.and(p2).test(30)); // -> false
		System.out.println(p1.and(p2).test(13)); // -> true
	}

	static void demoListRemoveIf() {
		mlog();
		// List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		//list.removeIf(x -> x % 2 == 0);
		//list.forEach(x -> System.out.println(x)); // 1 3 5
		
	}

	static void demoIsEqual() {
		mlog();
		Predicate<String> p = Predicate.isEqual(null);
		System.out.println(p.test(null));

	}
}
