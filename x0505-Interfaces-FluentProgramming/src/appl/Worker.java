package appl;

@FunctionalInterface
public interface Worker {

    int work(int data);

    default Worker andThen(Worker followingWorker) {
        return v -> {
            int newData = work(v);
            return followingWorker.work(newData);
        };
    }

}
