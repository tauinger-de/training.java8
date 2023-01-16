package appl;

public class Combiner implements Worker {

    private final Worker first;
    private final Worker second;

    public Combiner(Worker first, Worker second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int work(int value) {
        int newData = this.first.work(value);
        return this.second.work(newData);
    }
}
