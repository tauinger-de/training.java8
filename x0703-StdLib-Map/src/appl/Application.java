package appl;

import static java.lang.System.out;
import static util.Util.mlog;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

	static final List<String> words = Arrays.asList("eine", "Rose", "ist", "eine", "Rose", "ist", "eine", "Rose");

	public static void main(String[] args) {
		demoOldFashion();
		demoGetOrDefault();
		demoPutIfAbsentAndReplace();
		demoCompute();
	}

	static void demoOldFashion() {
		mlog();
		Map<String, Integer> counts = new HashMap<>();
		for (String word : words) {
			Integer count = counts.get(word);
			if (count == null) {
				count = 1;
				counts.put(word, count);
			}
			else {
				counts.put(word, count + 1);
			}
		}
		out.println(counts);
	}

	static void demoGetOrDefault() {
		mlog();
		Map<String, Integer> counts = new HashMap<>();
		for (String word : words) {
			// Integer count = counts.getOrDefault(word, 0);
			int count = counts.getOrDefault(word, 0);
			counts.put(word, count + 1);
		}
		out.println(counts);
	}

	static void demoPutIfAbsentAndReplace() {
		mlog();
		Map<String, Integer> counts = new HashMap<>();
		for (String word : words) {
			counts.putIfAbsent(word, 0);
			int count = counts.get(word);
			counts.replace(word, count + 1);
		}
		out.println(counts);
	}

	static void demoCompute() {
		mlog();
		Map<String, Integer> counts = new HashMap<>();
		for (String word : words) {
			counts.computeIfAbsent(word, (String k) -> 0);
			counts.computeIfPresent(word, (String k, Integer v) -> v + 1);
		}
		out.println(counts);
	}
}
