package appl;

import static util.Util.mlog;
import static util.Util.tlog;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.Util.XRunnable;

public class Application {

	public static void main(String[] args) throws Exception {

		demoRunAsync();
		demoRunAsyncExecutor();
		demoSupplyAsync();
		demoSupplyAsyncExecutor();
		demoAllOf();
		demoAnyOf();

		demoThenRun();
		demoThenAccept();
		demoThenApply();
		demoThenApplyThenAcceptThenRun();
		demoApplyToEither();
		demoApplyToEitherAsync();
		demoAcceptEither();
		demoRunAfterEither();

		demoRunAfterBoth();
		demoThenAcceptBoth();

		demoComplete1();
		demoComplete2();
		demoCompleteApply();

		demoCombine();
		demoCompose();

		demoWait();
		demoDifferentTypes1();
		demoDifferentTypes2();
		demoExceptions();
	}

	static void demoRunAsync() throws Exception {
		mlog();
		final CompletableFuture<Void> f = CompletableFuture.runAsync(
				() -> XRunnable.xrun(() -> Thread.sleep(1000)));
		tlog("f.isDone = " + f.isDone());
		final Void result = f.get();
		tlog("after f.get: " + result);
		tlog("f.isDone = " + f.isDone());
	}

	static void demoRunAsyncExecutor() throws Exception {
		final ExecutorService executor = Executors.newFixedThreadPool(2);
		mlog();
		final CompletableFuture<Void> f = CompletableFuture.runAsync(
				() -> XRunnable.xrun(() -> Thread.sleep(1000)), executor);
		final Void result = f.get();
		System.out.println(result);
		executor.shutdown();
	}

	static void demoSupplyAsync() throws Exception {
		mlog();
		final CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> 42);
		final Integer result = f.get();
		System.out.println(result);
	}

	static void demoSupplyAsyncExecutor() throws Exception {
		final ExecutorService executor = Executors.newFixedThreadPool(2);
		mlog();
		final CompletableFuture<Integer> f = CompletableFuture.supplyAsync(
				() -> 42, executor);
		final Integer result = f.get();
		System.out.println(result);
		executor.shutdown();
	}

	static void demoAllOf() throws Exception {
		mlog();
		final CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(
				() -> 42);
		final CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(
				() -> 77);
		CompletableFuture<Void> f = CompletableFuture.allOf(f1, f2);
		final Void result = f.get();
		System.out.println(result);
	}

	static void demoAnyOf() throws Exception {
		mlog();
		final CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(
				() -> 42);
		final CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(
				() -> 77);
		CompletableFuture<Object> f = CompletableFuture.anyOf(f1, f2);
		final Object result = f.get();
		System.out.println(result);
		f1.get();
		f2.get();
	}

	static void demoThenRun() throws Exception {
		mlog();
		final CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(
				() -> 42);
		final CompletableFuture<Void> f2 = f1.thenRun(
				() -> { });
		final Void result = f2.get();
		System.out.println(result);
	}

	static void demoThenAccept() throws Exception {
		mlog();
		final CompletableFuture<Void> f = CompletableFuture.supplyAsync(
				() -> 42)
				.thenAccept(x -> { });
		final Void result = f.get();
		System.out.println(result);
	}

	static void demoThenApply() throws Exception {
		mlog();
		final CompletableFuture<Integer> f = CompletableFuture.supplyAsync(
				() -> 42)
				.thenApply(x -> x + 1);
		final Integer result = f.get();
		System.out.println(result);
	}

	static void demoThenApplyThenAcceptThenRun() throws Exception {
		mlog();
		final CompletableFuture<Void> f = CompletableFuture.supplyAsync(
				() -> 42)
				.thenApply(x -> x + 1)
				.thenApply(x -> x + 1)
				.thenAccept(x -> { })
				.thenRun(() -> { });
		final Void result = f.get();
		System.out.println(result);
	}

	static void demoApplyToEither() throws Exception {
		mlog();
		final CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(
				() -> 42);
		final CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(
				() -> 77);
		final CompletableFuture<Integer> f = f1.applyToEither(f2,
				x -> x + 1);
		final Integer result = f.get();
		System.out.println(result);
		f1.get();
		f2.get();
	}

	static void demoApplyToEitherAsync() throws Exception {
		mlog();
		final CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(
				() -> 42);
		final CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(
				() -> 77);
		final CompletableFuture<Integer> f = f1.applyToEitherAsync(f2,
				x -> x + 1);
		final Integer result = f.get();
		System.out.println(result);
		f1.get();
		f2.get();
	}

	static void demoAcceptEither() throws Exception {
		mlog();
		final CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(
				() -> 42);
		final CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(
				() -> 77);
		final CompletableFuture<Void> f = f1.acceptEither(f2,
				x -> { });
		final Void result = f.get();
		System.out.println(result);
		f1.get();
		f2.get();
	}

	static void demoRunAfterEither() throws Exception {
		mlog();
		final CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(
				() -> 42);
		final CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(
				() -> 77);
		final CompletableFuture<Void> f = f1.runAfterEither(f2,
				() -> { });
		final Void result = f.get();
		System.out.println(result);
		f1.get();
		f2.get();
	}

	static void demoRunAfterBoth() throws Exception {
		mlog();
		final CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(
				() -> 42);
		final CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(
				() -> 77);
		final CompletableFuture<Void> f = f1.runAfterBoth(f2,
				() -> { });
		final Void result = f.get();
		System.out.println(result);
	}

	static void demoThenAcceptBoth() throws Exception {
		mlog();
		final CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(
				() -> 42);
		final CompletableFuture<String> f2 = CompletableFuture.supplyAsync(
				() -> "Hello");
		final CompletableFuture<Void> f = f1.thenAcceptBoth(f2,
				(i, s) -> { });
		final Void result = f.get();
		System.out.println(result);
	}

	static void demoComplete1() throws Exception {
		mlog();
		final CompletableFuture<Integer> f = new CompletableFuture<>();
		f.complete(42);
		final Integer result = f.get();
		System.out.println(result);
	}

	static void demoComplete2() throws Exception {
		mlog();
		final CompletableFuture<Integer> f = new CompletableFuture<>();
		new Thread() {
			public void run() {
				XRunnable.xrun(() -> Thread.sleep(1000));
				f.complete(42);
			}
		}.start();
		final Integer result = f.get();
		System.out.println(result);
	}

	static void demoCompleteApply() throws Exception {
		mlog();
		final CompletableFuture<Integer> f = new CompletableFuture<Integer>()
				.thenApply(x -> x + 1); 
		new Thread() {
			public void run() {
				XRunnable.xrun(() -> Thread.sleep(1000));
				f.complete(42);
			}
		}.start();
		final Integer result = f.get();
		System.out.println(result);
	}

	static void demoCombine() throws Exception {
		mlog();
		final CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(
				() -> 42);
		final CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(
				() -> 77);
		final CompletableFuture<Integer> f3 = f1.thenCombine(f2,
				(x, y) -> x + y);
		final Integer result = f3.get();
		System.out.println(result);
	}

	static void demoCompose() throws Exception {
		mlog();
		final CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(
				() -> 42);
		final CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(
				() -> 77);
		final CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(
				() -> -1)
				.thenCompose(
						x -> x > 0 ? f1 : f2);
		final Integer result = f3.get();
		System.out.println(result);
	}

	static void demoWait() throws Exception {
		mlog();
		final CompletableFuture<Integer> f = new CompletableFuture<>();
		class MyThread extends Thread {
			@Override
			public void run() {
				try {
					final int value = f.get();
					System.out.println(value);
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		for (int i = 0; i < 5; i++)
			new MyThread().start();
		XRunnable.xrun(() -> Thread.sleep(1000));
		f.complete(42);
		final int result = f.get();
		System.out.println(result);
	}

	static void demoDifferentTypes1() throws Exception {
		mlog();
		final CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "10");
		final CompletableFuture<Integer> f2 = f1.thenApply(Integer::parseInt);
		final CompletableFuture<Double> f3 = f2.thenApply(r -> r * r * Math.PI);
		final Double result = f3.get();
		System.out.println(result);
	}

	static void demoDifferentTypes2() throws Exception {
		mlog();
		CompletableFuture<Double> f = CompletableFuture.supplyAsync(() -> "10")
				.thenApply(Integer::parseInt)
				.thenApply(r -> r * r * Math.PI);
		Double result = f.get();
		System.out.println(result);
	}

	static void demoExceptions() throws Exception {
		mlog();
		CompletableFuture<Void> f = CompletableFuture.supplyAsync(() -> {
			return 42;
		}).thenApplyAsync((i) -> {
			tlog("thenApply");
			throw new RuntimeException("abc");
		}).exceptionally(e -> {
			tlog("exceptionally");
			return -1;
		}).thenAcceptAsync((i) -> {
			tlog("i = " + i);
		});
		Void result = f.get();
		System.out.println(result);
	}
}
