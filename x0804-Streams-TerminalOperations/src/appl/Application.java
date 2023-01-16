package appl;

import static util.Util.mlog;
import static util.Util.tlog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import util.Util;

public class Application {

	public static void main(String[] args) {
		demoForEachSequential();
		demoForEachParallel();
		demoForEachOrdered();

		demoToObjectArray();
		demoToIntegerArray();
		demoToIntArray();

		demoReduce();
		demoReduceEmpty();
		demoReduceOptional();
		demoReduceParallel();

		demoCollectSequential();
		demoCollectParallel();
		demoCollector1();
		demoCollector2();
		demoCollectorsToList();

		demoMinMaxCount();
		demoMatch();
		demoFind();
	}

	static void demoForEachSequential() {
		mlog();
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).sequential();
		stream.forEach(s -> tlog("forEach: " + s));
		System.out.println();
	}

	static void demoForEachParallel() {
		mlog();
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
		stream.forEach(s -> tlog("forEach: " + s));
		System.out.println();
	}

	static void demoForEachOrdered() {
		mlog();
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
		stream.forEachOrdered(s -> tlog("forEach: " + s));
		System.out.println();
	}

	static void demoToObjectArray() {
		mlog();
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
		Object[] array = stream.toArray();
		for (Object v : array)
			System.out.print(v + " ");
		System.out.println();
	}

	static void demoToIntegerArray() {
		mlog();
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
		Integer[] array = stream.toArray(n -> new Integer[n]);
		for (Integer v : array)
			System.out.print(v + " ");
		System.out.println();
	}

	static void demoToIntArray() {
		mlog();
		IntStream stream = IntStream.of(10, 20, 30, 40, 50);
		int[] array = stream.toArray();
		for (int v : array)
			System.out.print(v + " ");
		System.out.println();
	}

	static void demoReduce() {
		mlog();
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
		Integer sum = stream.reduce(0, (x, y) -> {
			System.out.println(x + " " + y);
			return x + y;
		});
		System.out.println("sum = " + sum);
		//System.out.println("sum = " +Stream.of(10, 20, 30, 40, 50).reduce(0, (x, y) -> x + y));
		
	}

	static void demoReduceEmpty() {
		mlog();
		Stream<Integer> stream = Stream.empty();
		Integer sum = stream.reduce(0, (x, y) -> x + y);
		System.out.println(sum);
	}

	static void demoReduceOptional() {
		mlog();
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
		Optional<Integer> result = stream.reduce((x, y) -> x + y);
		int sum = result.get();
		System.out.println(sum);
	}

	static void demoReduceParallel() {
		mlog();
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
		Integer product = stream.reduce(1, (x, y) -> {
			Util.tlog("==> " + x + " " + y);
			return x * y; 
		});
		System.out.println(product);
	}

	static void demoCollectSequential() {
		mlog();
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
		List<Integer> list = stream.collect(
				() -> new ArrayList<Integer>(), 
				(List<Integer> l, Integer v) -> l.add(v), 
				(List<Integer> l1, List<Integer> l2) -> l1.addAll(l2));
		System.out.println(list);
	}

	static void demoCollectParallel() {
		mlog();
		// Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50).parallel();
		List<Integer> list = stream.collect(
				() -> {
					tlog("supply");
					return new ArrayList<Integer>();
				},
				(List<Integer> l, Integer v) -> l.add(v), 
				(List<Integer> l1, List<Integer> l2) -> {
					tlog("combine " + l1 + " and " + l2);
					l1.addAll(l2);
				}
		);
		System.out.println(list);
	}

	static void demoCollector1() {
		mlog();
		Collector<Integer, List<Integer>, Integer[]> collector = new Collector<Integer, List<Integer>, Integer[]>() {
			public Supplier<List<Integer>> supplier() {
				return () -> new ArrayList<Integer>();
			}

			public BiConsumer<List<Integer>, Integer> accumulator() {
				return (l, v) -> l.add(v);
			}

			public BinaryOperator<List<Integer>> combiner() {
				return (l1, l2) -> {
					l1.addAll(l2);
					return l1;
				};
			}

			public Function<List<Integer>, Integer[]> finisher() {
				return l -> l.toArray(new Integer[l.size()]);
			}

			public Set<Characteristics> characteristics() {
				return new HashSet<Characteristics>();
			}
		};
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
		Integer[] array = stream.collect(collector);
		System.out.println(Arrays.toString(array));
		Collectors.toList();
	}

	static void demoCollector2() {
		mlog();
		Collector<Integer, List<Integer>, List<Integer>> collector = new Collector<Integer, List<Integer>, List<Integer>>() {
			public Supplier<List<Integer>> supplier() {
				return () -> new ArrayList<Integer>();
			}

			public BiConsumer<List<Integer>, Integer> accumulator() {
				return (l, v) -> l.add(v);
			}

			public BinaryOperator<List<Integer>> combiner() {
				return (l1, l2) -> {
					l1.addAll(l2);
					return l1;
				};
			}

			public Function<List<Integer>, List<Integer>> finisher() {
				return l -> l;
			}

			public Set<Characteristics> characteristics() {
				return new HashSet<Characteristics>();
			}
		};
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
		List<Integer> list = stream.collect(collector);
		System.out.println(list);
	}

	static void demoCollectorsToList() {
		mlog();
		Stream<Integer> stream = Stream.of(10, 20, 30, 40, 50);
		List<Integer> list = stream.collect(Collectors.toList());
		System.out.println(list);
	}

	static void demoMinMaxCount() {
		mlog();
		Stream<Integer> stream1 = Stream.of(10, 20, 30, 40, 50).parallel();
		System.out.println(stream1.min((x, y) -> x.compareTo(y)));

		Stream<Integer> stream2 = Stream.of(10, 20, 30, 40, 50).parallel();
		System.out.println(stream2.max((x, y) -> x.compareTo(y)));

		Stream<Integer> stream3 = Stream.of(10, 20, 30, 40, 50).parallel();
		System.out.println(stream3.count());
	}

	static void demoMatch() {
		mlog();
		Stream<Integer> stream1 = Stream.of(10, 20, 30, 40, 50).parallel();
		System.out.println(stream1.anyMatch(x -> x == 20));

		Stream<Integer> stream2 = Stream.of(10, 20, 30, 40, 50).parallel();
		System.out.println(stream2.allMatch(x -> x <= 50));

		Stream<Integer> stream3 = Stream.of(10, 20, 30, 40, 50).parallel();
		System.out.println(stream3.noneMatch(x -> x == 42));
	}

	static void demoFind() {
		mlog();
		Stream<Integer> stream1 = Stream.of(10, 20, 30, 40, 50).parallel();
		System.out.println(stream1.findAny()); // mal so, mal so....

		Stream<Integer> stream2 = Stream.of(10, 20, 30, 40, 50).parallel();
		System.out.println(stream2.findFirst());
	}
}
