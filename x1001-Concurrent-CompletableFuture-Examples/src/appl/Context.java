package appl;

import java.util.concurrent.CompletableFuture;

public class Context<S,E> {
	public final CompletableFuture<S> start;
	public final CompletableFuture<E> end;
	public Context(CompletableFuture<S> start, CompletableFuture<E> end) {
		this.start = start;
		this.end = end;
	}
	public Context<S,E> complete(S value) {
		this.start.complete(value);
		return this;
	}
	public E get() throws Exception {
		return this.end.get();
	}
}
