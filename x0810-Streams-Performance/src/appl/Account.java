package appl;

public class Account {
    // quick & dirty....
    private final int number;
    private final Customer customer;
    private int balance;

    public Account(int number, Customer customer, int balance) {
        this.number = number;
        this.customer = customer;
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + number + ", " + customer + ", " + balance + "]";
    }
}
