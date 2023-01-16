package appl;

@FunctionalInterface
public interface WorkerC {
	public abstract void work(int value);
	public default WorkerC andThen(WorkerC other) {
		return v -> { 
			work(v); 
			other.work(v); 
		}; 
	}
}
