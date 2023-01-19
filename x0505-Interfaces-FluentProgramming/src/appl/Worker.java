package appl;

@FunctionalInterface
public interface Worker {

    int workOn(int data);

    default Worker andThen(Worker followingWorker) {
        return v -> {
            int newData = workOn(v);
            return followingWorker.workOn(newData);
        };
    }

}
