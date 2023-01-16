package appl;

import static util.Util.mlog;
import static util.functional.proxies.TraceAroundAdvice.traceBiFunction;
import static util.functional.proxies.TraceAroundAdvice.traceFunction;
import static util.functional.proxies.TraceAroundAdvice.traceSupplier;

import java.util.concurrent.CompletableFuture;

import util.Util.XRunnable;
import util.functional.proxies.TLog;
import util.workviewer.Work;

public class Application {

	static void call(XRunnable runnable) {
		Work.useViewer();
		Work.viewer.start();
		TLog.start();
		XRunnable.xrun(runnable);
		Work.viewer.stop();
	}
	
	public static void main(String[] args) throws Exception {


		demoPoductIncAndDec();
		
		demoPythagoras();
		call(() -> demoPythagorasTrace());
		
		demoPythagorasComplete();
		demoPythagorasCompleteWithPair();
		
		demoProductOfFourSums();
		call(() -> demoProductOfFourSumsTrace());
		
		demoBuildAndCalculate();
	}

	static void demoPoductIncAndDec() throws Exception {
		mlog();

		final int value = 4;
		
		final CompletableFuture<Integer> s = CompletableFuture.supplyAsync(
          () -> value);

		final CompletableFuture<Integer> f0 = s.thenApplyAsync(
          x -> x + 1);

		final CompletableFuture<Integer> f1 = s.thenApplyAsync(
          x -> x - 1);

		final CompletableFuture<Integer> f = f0.thenCombine(f1, 
          (x, y) -> x * y);

		int result = f.get();
		System.out.println(result);
	}

	static void demoPythagoras() throws Exception {
		mlog();
		double a = 3.0;
		double b = 4.0;
		final CompletableFuture<Double> s0 = CompletableFuture.supplyAsync(() -> a);
		final CompletableFuture<Double> s1 = CompletableFuture.supplyAsync(() -> b);
		final CompletableFuture<Double> f0 = s0.thenApplyAsync(x -> x * x);
		final CompletableFuture<Double> f1 = s1.thenApplyAsync(x -> x * x);
		final CompletableFuture<Double> bif = f0.thenCombine(f1, (x, y) -> x + y);
		final CompletableFuture<Double> f = bif.thenApply(x -> Math.sqrt(x));
		Double result = f.get();
		System.out.println(result);
	}

	static void demoPythagorasTrace() throws Exception {
		mlog();
		double a = 3.0;
		double b = 4.0;

		final CompletableFuture<Double> s0 = CompletableFuture.supplyAsync(
				traceSupplier("s0", 1000, () -> a));

		final CompletableFuture<Double> s1 = CompletableFuture.supplyAsync(
				traceSupplier("s1", 1500, () -> b));

		final CompletableFuture<Double> f0 = s0.thenApplyAsync(
				traceFunction("f0", 1000, x -> x * x));

		final CompletableFuture<Double> f1 = s1.thenApplyAsync(
				traceFunction("f1", 2000, x -> x * x));

		final CompletableFuture<Double> bif = f0.thenCombine(f1,
				traceBiFunction("bif", 1000, (x, y) -> x + y));

		final CompletableFuture<Double> f = bif.thenApply(
				traceFunction("f", 1000, x -> Math.sqrt(x)));

		final Double result = f.get();
		System.out.println(result);
	}

	static void demoPythagorasComplete() throws Exception {
		mlog();
		final CompletableFuture<Double> future0 = new CompletableFuture<>();
		final CompletableFuture<Double> future1 = new CompletableFuture<>();
		final CompletableFuture<Double> f0 = future0.thenApplyAsync(x -> x * x);
		final CompletableFuture<Double> f1 = future1.thenApplyAsync(x -> x * x);
		final CompletableFuture<Double> bif = f0.thenCombine(f1, (x, y) -> x + y);
		final CompletableFuture<Double> f = bif.thenApply(x -> Math.sqrt(x));
		System.out.println("before complete...");
		future0.complete(3.0);
		future1.complete(4.0);
		final Double result = f.get();
		System.out.println(result);
	}

	static void demoPythagorasCompleteWithPair() throws Exception {
		mlog();
		final CompletableFuture<Pair<Double, Double>> future = new CompletableFuture<>();
		final CompletableFuture<Double> f0 = future.thenApplyAsync(p -> p.x * p.x);
		final CompletableFuture<Double> f1 = future.thenApplyAsync(p -> p.y * p.y);
		final CompletableFuture<Double> bif = f0.thenCombine(f1, (x, y) -> x + y);
		final CompletableFuture<Double> f = bif.thenApply(x -> Math.sqrt(x));
		future.complete(new Pair<>(4.0, 3.0));
		final Double result = f.get();
		System.out.println(result);
	}
	
	static void demoProductOfFourSums() throws Exception {
		// (value + 1) * (value + 2) * (value + 3) * (value + 4)
		mlog();
		final CompletableFuture<Integer> future = new CompletableFuture<>();

		final CompletableFuture<Integer> f0 = future.thenApplyAsync(x -> x + 1);
		final CompletableFuture<Integer> f1 = future.thenApplyAsync(x -> x + 2);
		final CompletableFuture<Integer> f2 = future.thenApplyAsync(x -> x + 3);
		final CompletableFuture<Integer> f3 = future.thenApplyAsync(x -> x + 4);

		final CompletableFuture<Integer> bif0 = f0.thenCombine(f1, (x, y) -> x * y);
		final CompletableFuture<Integer> bif1 = f2.thenCombine(f3, (x, y) -> x * y);

		final CompletableFuture<Integer> bif = bif0.thenCombine(bif1, (x, y) -> x * y);

		future.complete(2);
		final int result = bif.get();
		System.out.println(result);
	}
	
	static void demoProductOfFourSumsTrace() throws Exception {
		mlog();
		final CompletableFuture<Integer> future = new CompletableFuture<>();

		final CompletableFuture<Integer> f0 = future.thenApplyAsync(
			traceFunction("f0", 1000, x -> x + 1));
			
		final CompletableFuture<Integer> f1 = future.thenApplyAsync(
			traceFunction("f2", 1500, x -> x + 2));

		final CompletableFuture<Integer> f2 = future.thenApplyAsync(
			traceFunction("f3", 1000, x -> x + 3));
		
		final CompletableFuture<Integer> f3 = future.thenApplyAsync(
			traceFunction("f4", 2000, x -> x + 4));

		final CompletableFuture<Integer> bif0 = f0.thenCombine(f1, 
			traceBiFunction("bif0", 1000, (x, y) -> x * y));
				
		final CompletableFuture<Integer> bif1 = f2.thenCombine(f3, 
			traceBiFunction("bif1", 1000, (x, y) -> x * y));

		final CompletableFuture<Integer> bif = bif0.thenCombine(bif1, 
			traceBiFunction("bif", 1000, (x, y) -> x * y));

		System.out.println("before complete...");
		future.complete(2);
		final int result = bif.get();
		System.out.println(result);
	}

	static Context<Integer, Integer> buildContext() {
		final CompletableFuture<Integer> f1 = new CompletableFuture<>();
		final CompletableFuture<Integer> f2 = f1.thenApplyAsync(x -> x + 1);
		final CompletableFuture<Integer> f3 = f1.thenApplyAsync(x -> x - 1);
		final CompletableFuture<Integer> f4 = f2.thenCombine(f3, (x1, x2) -> x1 * x2);
		return new Context<>(f1, f4);
	}
	
	static void demoBuildAndCalculate() throws Exception {
		mlog();
		System.out.println(buildContext().complete(3).get());
		System.out.println(buildContext().complete(4).get());
		System.out.println(buildContext().complete(5).get());

	}

}
