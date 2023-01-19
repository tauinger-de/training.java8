package appl;

@SuppressWarnings("unused")
public class Combiner implements Worker {

    private final Worker first;
    private final Worker second;

    public Combiner(Worker first, Worker second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int workOn(int value) {
        int newData = this.first.workOn(value);
        return this.second.workOn(newData);
    }
}
