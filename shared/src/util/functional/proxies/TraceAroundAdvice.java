package util.functional.proxies;

import static util.Util.XRunnable.xrun;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import util.workviewer.Work;

public class TraceAroundAdvice extends AroundAdvice {
	
	private final String description;
	private final int workTime;
	
	public TraceAroundAdvice(String description, int workTime) {
		this.description = description;
		this.workTime = workTime;
	}
	
	@Override
	public void before(Object... args) {
		final String msg = this.description + " " + this.proxy.methodName + Arrays.toString(args);
		TLog.logEnter(msg);
		Work.viewer.beginWork(msg, this.proxy.color);
		xrun(() -> Thread.sleep(this.workTime));  
	}
	
	@Override
	public void after(Object result) {
		Work.viewer.endWork();
		TLog.logExit(this.description + " " + result);
	}

	public static <T, R> Function<T, R> traceFunction(String description, int workTime, Function<T, R> function) {
		return new FunctionProxy<>(function, new TraceAroundAdvice(description, workTime));
	}

	public static <T0, T1, R> BiFunction<T0, T1, R> traceBiFunction(String description, int workTime, BiFunction<T0, T1, R> biFunction) {
		return new BiFunctionProxy<>(biFunction, new TraceAroundAdvice(description, workTime));
	}

	public static <T> Predicate<T> tracePredicate(String description, int workTime, Predicate<T> predicate) {
		return new PredicateProxy<>(predicate, new TraceAroundAdvice(description, workTime));
	}

	public static <T> Consumer<T> traceConsumer(String description, int workTime, Consumer<T> consumer) {
		return new ConsumerProxy<>(consumer, new TraceAroundAdvice(description, workTime));
	}

	public static <R> Supplier<R> traceSupplier(String description, int workTime, Supplier<R> supplier) {
		return new SupplierProxy<>(supplier, new TraceAroundAdvice(description, workTime));
	}

	public static Runnable traceRunnable(String description, int workTime, Runnable runnable) {
		return new RunnableProxy(runnable, new TraceAroundAdvice(description, workTime));
	}
	
}

