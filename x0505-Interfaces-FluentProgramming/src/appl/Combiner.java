package appl;

public class Combiner implements WorkerA {
    private final WorkerA first;
    private final WorkerA second;

    public Combiner(WorkerA first, WorkerA second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void work(int value) {
        this.first.work(value);
        this.second.work(value);
    }
}
