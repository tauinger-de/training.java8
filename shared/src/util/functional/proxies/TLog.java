package util.functional.proxies;

import java.util.HashMap;
import java.util.Map;

public class TLog {

	private static Object lock = new Object();

	public static boolean enabled = true;
	
	private static final Map<Long, Integer> indents = new HashMap<>();
	private static int indent = 0;

	public static void start() {
		indent = 0;
		indents.clear();
	}
	
	public static void logEnter(String msg) {
		if (! enabled)
			return;
		synchronized (lock) {
			printIndent();
			System.out.printf("[%2d] >> %s\n", Thread.currentThread().getId(), msg);
		}
	}
	public static void logExit(String msg) {
		if (! enabled)
			return;
		synchronized (lock) {
			printIndent();
			System.out.printf("[%2d] << %s\n", Thread.currentThread().getId(), msg);
		}
	}
	
	public static void log(Object msg) {
		if (! enabled)
			return;
		synchronized (lock) {
			printIndent();
			System.out.printf("[%2d] -- %s\n", Thread.currentThread().getId(), msg);
		}
	}

	private static void printIndent() {
		int i = indents.computeIfAbsent(Thread.currentThread().getId(), k -> indent++);
		while (i --> 0)
			System.out.print("    ");
	}
}
