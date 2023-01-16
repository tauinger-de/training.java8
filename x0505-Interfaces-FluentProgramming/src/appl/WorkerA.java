package appl;

public interface WorkerA {

    public abstract void work(int value);

    public default WorkerA andThen(final WorkerA other) {
        return new Combiner(this, other);
    }
}
