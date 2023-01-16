package appl;

public interface Account {
    public abstract void withdraw(int amount);

    public abstract int getAvailable();

    public static void check(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException();
    }
}
