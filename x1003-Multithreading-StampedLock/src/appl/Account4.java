package appl;

import java.util.concurrent.locks.StampedLock;

import static util.Util.XRunnable.xrun;
import static util.Util.tlog;

public class Account4 implements Account {

    private int balance;
    private int credit;

    private final StampedLock lock = new StampedLock();

    public Account4(int balance, int credit) {
        Account.check(balance);
        Account.check(balance);
        this.balance = balance;
        this.credit = credit;
    }

    public void withdraw(int amount) {
        Account.check(amount);
        final long stamp = this.lock.writeLock();
        tlog("\t\twithdraw: after writeLock : " + stamp);
        try {
            if (amount > this.balance + this.credit)
                throw new IllegalArgumentException();
            xrun(() -> Thread.sleep(1000));
            this.balance -= amount;
        } finally {
            tlog("\t\twithdraw: before unlock");
            this.lock.unlockWrite(stamp);
        }
    }

    public int getAvailable() {
        long stamp = this.lock.tryOptimisticRead();
        int result = this.balance + this.credit;
        if (this.lock.validate(stamp)) {
            tlog("\t\tvalidate okay");
            return result;
        }
        stamp = lock.readLock();
        tlog("\t\tgetAvailable: after readLock : " + stamp);
        try {
            return this.balance + this.credit;
        } finally {
            tlog("\t\tgetAvailable: before unlock");
            this.lock.unlockRead(stamp);
        }
    }
}
