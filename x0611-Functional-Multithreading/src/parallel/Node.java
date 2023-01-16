package parallel;

import static util.Util.XCallable.xcall;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Node<T> {

	//public static final ExecutorService executor = Executors.newCachedThreadPool();
	public static final ExecutorService executor = Executors.newFixedThreadPool(10);

	public abstract T get();

	private static class SupplierNode<T> extends Node<T> {
		public final Supplier<T> supplier;

		public SupplierNode(Supplier<T> supplier) {
			this.supplier = supplier;
		}

		private T result = null;

		synchronized public T get() {
			if (this.result == null) {
				Future<T> future = executor.submit(() -> this.supplier.get());
				this.result = xcall(() -> future.get());
			}
			return this.result;
		}
	}

	private static class FunctionNode<T, R> extends Node<R> {
		private final Node<T> previous;
		public final Function<T, R> function;

		public FunctionNode(Function<T, R> function, Node<T> previous) {
			this.previous = previous;
			this.function = function;
		}

		private R result = null;

		synchronized public R get() {
			if (this.result == null) {
				Future<R> future = executor.submit(() -> {
					T value = this.previous.get();
					return function.apply(value);
				});
				result = xcall(() -> future.get());
			}
			return result;
		}
	}

	private static class BiFunctionNode<T0, T1, R> extends Node<R> {
		private final Node<T0> previous0;
		private final Node<T1> previous1;
		public final BiFunction<T0, T1, R> function;

		public BiFunctionNode(BiFunction<T0, T1, R> function, Node<T0> previous0, Node<T1> previous1) {
			this.previous0 = previous0;
			this.previous1 = previous1;
			this.function = function;
		}

		private R result = null;

		synchronized public R get() {
			if (this.result == null) {
				Future<T0> future0 = executor.submit(() -> {
					return this.previous0.get();
				});
				Future<T1> future1 = executor.submit(() -> {
					return this.previous1.get();
				});
				T0 value0 = xcall(() -> future0.get());
				T1 value1 = xcall(() -> future1.get());
				Future<R> future = executor.submit(() -> {
					return function.apply(value0, value1);
				});
				result = xcall(() -> future.get());
			}
			return result;
		}
	}

	private static class ConsumerNode<T> extends Node<Void> {
		private final Node<T> previous;
		public final Consumer<T> consumer;

		public ConsumerNode(Consumer<T> consumer, Node<T> previous) {
			this.previous = previous;
			this.consumer = consumer;
		}

		public Void get() {
			Future<Void> future = executor.submit(() -> {
				T value = this.previous.get();
				consumer.accept(value);
				return null;
			});
			return xcall(() -> future.get());
		}
	}

	private static class RunnableNode extends Node<Void> {
		private final Node<?> previous;
		public final Runnable runnable;

		public RunnableNode(Runnable runnable, Node<?> previous) {
			this.previous = previous;
			this.runnable = runnable;
		}

		public Void get() {
			Future<Void> future = executor.submit(() -> {
				this.previous.get();
				runnable.run();
				return null;
			});
			return xcall(() -> future.get());
		}
	}

	public static <T> Node<T> supply(Supplier<T> supplier) {
		return new SupplierNode<T>(supplier);
	}

	public <R> Node<R> apply(Function<T, R> function) {
		return new FunctionNode<T, R>(function, this);
	}

	public <T0, R> Node<R> combine(Node<T0> other, BiFunction<T, T0, R> function) {
		return new BiFunctionNode<T, T0, R>(function, this, other);
	}

	public Node<Void> accept(Consumer<T> consumer) {
		return new ConsumerNode<T>(consumer, this);
	}

	public Node<Void> run(Runnable runnable) {
		return new RunnableNode(runnable, this);
	}
}
