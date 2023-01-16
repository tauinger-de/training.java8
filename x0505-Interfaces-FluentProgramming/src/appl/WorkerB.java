package appl;

@FunctionalInterface
public interface WorkerB {
	
	public abstract void work(int value);

	public default WorkerB andThen(final WorkerB other) {
		return new WorkerB() {
			public void work(int v) { 
				WorkerB.this.work(v); 
				other.work(v);
			}
		}; 
	}
}
