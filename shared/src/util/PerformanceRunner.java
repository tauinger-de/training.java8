package util;

public class PerformanceRunner {
	public void run(String msg, int times, Runnable runnable) {
		final long start = System.nanoTime();
		try {
			for (int i = times; i > 0; --i) {
				runnable.run();
			}
		}
		catch (Throwable t) {
			System.out.println(t);
		}
		final long end = System.nanoTime();
		System.out.printf("%-30s : %5d\n", msg, (end - start) / 1_000_000);
	}
	public void run(String msg, int times, Runnable initRunnable, Runnable runnable) {
		long duration = 0;
		try {
			for (int i = times; i > 0; --i) {
				initRunnable.run();
				final long start = System.nanoTime();
				runnable.run();
				final long end = System.nanoTime();
				duration += (end - start);
			}
		}
		catch (Throwable t) {
			System.out.println(t);
		}
		System.out.printf("%-30s : %5d\n", msg, duration / 1_000_000);
	}
}
