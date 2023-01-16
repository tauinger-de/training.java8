package appl;

import java.util.Arrays;
import java.util.List;

import utils.Stream;

public class Application {
	public static void main(String[] args) {
		demo1();
		demo2();
		demo3();
		demo4();
		demo5();
		demo6();
	}
	static void demo1() {
		System.out.println("============ demo1 =============");
		Stream<Integer> s1 = Stream.create(Arrays.asList(10, 11, 12, 13, 14));
		Stream<Integer> s2 = s1.filter(x -> x % 2 == 0);
		Stream<Integer> s3 = s2.map(x -> x * 2);
		s3.forEach(x -> System.out.println(x));
	}
	
	static void demo2() {
		System.out.println("============ demo2 =============");
		Stream.create(Arrays.asList(10, 11, 12, 13, 14))
			.filter(x -> x % 2 == 0)
			.map(x -> x * 2)
			.forEach(x -> System.out.println(x));
	}

	static void demo3() {
		System.out.println("============ demo3 =============");
		Stream.create(Arrays.asList("red", "green", "blue"))
			.filter(s -> s.length() > 3)
			.map(s -> s.length()) 
			.filter(l -> l % 2 == 0) 
			.forEach(v -> System.out.println(v));
	}

	static void demo4() {
		System.out.println("============ demo4 =============");
		List<String> result = Stream.create(Arrays.asList("red", "green", "blue"))
			.filter(s -> s.length() > 3)
			.collect();
		System.out.println(result);
	}

	static void demo5() {
		System.out.println("============ demo5 =============");
		int result = Stream.create(Arrays.asList(10, 11, 12, 13, 14))
			.reduce(0, (x, y) -> x + y);
		System.out.println(result);
	}

	static void demo6() {
		System.out.println("============ demo6 =============");
		Stream.create(Arrays.asList(10, 11, 12, 13, 14))
			.filter(x -> x % 2 == 0)
			.peek(x -> System.out.println("\t " + x))
			.map(x -> x * 2)
			.forEach(x -> System.out.println(x));
	}
	
}
