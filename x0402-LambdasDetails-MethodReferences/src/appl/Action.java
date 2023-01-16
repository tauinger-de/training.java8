package appl;

@FunctionalInterface
public interface Action<T> {

    void execute(T arg);

}
