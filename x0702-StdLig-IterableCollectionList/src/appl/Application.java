package appl;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static util.Util.mlog;

public class Application {

	public static void main(String[] args) {
		demoForEach();
		demoRemoveIf();
		demoSort();
		demoReplaceAll();
	}

	static void demoForEach() {
		mlog();
		Iterable<Integer> list = Arrays.asList(20, 40, 10, 30);
		list.forEach(out::println);
		out.println();
	}

	static void demoRemoveIf() {
		mlog();
		// Collection<Integer> list = Arrays.asList(20, 40, 10, 30);
		Collection<Integer> list = new ArrayList<>(Arrays.asList(20, 40, 10, 30));
		list.removeIf(elem -> elem >= 30);
		list.forEach(s -> out.print(s + " "));
		out.println();
	}

	static void demoSort() {
		mlog();
		List<Integer> list = Arrays.asList(20, 40, 10, 30);
		// list.sort((i0, i1) -> i0.compareTo(i1)); // List
		list.sort(Comparator.naturalOrder()); // List
		list.forEach(s -> out.print(s + " "));
		out.println();
	}

	static void demoReplaceAll() {
		mlog();
		List<Integer> list = Arrays.asList(20, 40, 10, 30);
		list.replaceAll(elem -> 2 * elem); // List
		list.forEach(s -> out.print(s + " "));
		out.println();
	}
}
