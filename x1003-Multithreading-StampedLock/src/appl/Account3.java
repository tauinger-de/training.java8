package appl;

import java.util.concurrent.locks.StampedLock;

import static util.Util.XRunnable.xrun;
import static util.Util.tlog;

public class Account3 implements Account {

    private int balance;
    private int credit;

    private final StampedLock lock = new StampedLock();
    private final ThreadLocal<Long> stamps = new ThreadLocal<>();

    public Account3(int balance, int credit) {
        Account.check(balance);
        Account.check(balance);
        this.balance = balance;
        this.credit = credit;
    }

    public void withdraw(int amount) {
        Account.check(amount);
        stamps.set(this.lock.writeLock());
        tlog("\t\twithdraw: after writeLock : " + stamps.get());
        try {
            if (amount > this.getAvailable())
                throw new IllegalArgumentException();
            xrun(() -> Thread.sleep(1000));
            this.balance -= amount;
        } finally {
            tlog("\t\twithdraw: before unlock");
            this.lock.unlockWrite(stamps.get());
            stamps.remove();
        }
    }

    public int getAvailable() {
        long stamp = 0;
        Long s = stamps.get();
        if (s == null)
            stamp = this.lock.readLock();
        tlog("\t\tgetAvailable: after readLock : " + stamp);
        try {
            return this.balance + this.credit;
        } finally {
            tlog("\t\tgetAvailable: before unlock");
            if (s == null)
                this.lock.unlockRead(stamp);
        }
    }
}
