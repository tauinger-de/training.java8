package appl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static util.Util.XRunnable.xrun;
import static util.Util.tlog;

public class Account1 implements Account {

    private int balance;
    private int credit;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public Account1(int balance, int credit) {
        Account.check(balance);
        Account.check(credit);
        this.balance = balance;
        this.credit = credit;
    }

    public void withdraw(int amount) {
        Account.check(amount);
        final Lock l = this.lock.writeLock();
        l.lock();
        tlog("\t\twithdraw: after writeLock");
        try {
            if (amount > this.getAvailable())
                throw new IllegalArgumentException();
            xrun(() -> Thread.sleep(1000));
            this.balance -= amount;
        } finally {
            tlog("\t\twithdraw: before unlock");
            l.unlock();
        }
    }

    public int getAvailable() {
        final Lock l = this.lock.readLock();
        l.lock();
        tlog("\t\tgetAvailable: after readLock");
        try {
            return this.balance + this.credit;
        } finally {
            tlog("\t\tgetAvailable: before unlock");
            l.unlock();
        }
    }
}
