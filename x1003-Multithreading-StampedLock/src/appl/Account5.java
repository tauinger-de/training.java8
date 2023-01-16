package appl;

import java.util.concurrent.locks.StampedLock;

import static util.Util.XRunnable.xrun;
import static util.Util.tlog;

public class Account5 implements Account {

    private int balance;
    private int credit;

    private final StampedLock lock = new StampedLock();

    public Account5(int balance, int credit) {
        Account.check(balance);
        Account.check(balance);
        this.balance = balance;
        this.credit = credit;
    }

    public void withdraw(int amount) {
        Account.check(amount);
        long stamp = this.lock.readLock();
        tlog("\t\twithdraw: after readLock : " + stamp);
        try {
            if (amount > this.balance + this.credit)
                throw new IllegalArgumentException();
            long writeStamp = this.lock.tryConvertToWriteLock(stamp);
            if (writeStamp == 0) {
                this.lock.unlock(stamp);
                tlog("\t\twithdraw: unlock readLock : " + stamp);
                stamp = this.lock.writeLock();
                tlog("\t\twithdraw: after writeLock");
            } else {
                tlog("\t\twithdraw: convert done");
                stamp = writeStamp;
            }
            xrun(() -> Thread.sleep(1000));
            this.balance -= amount;
        } finally {
            tlog("\t\twithdraw: before unlock");
            this.lock.unlock(stamp);
        }
    }

    public int getAvailable() {
        final long stamp = this.lock.readLock();
        tlog("\t\tgetAvailable: after readLock : " + stamp);
        try {
            return this.balance + this.credit;
        } finally {
            tlog("\t\tgetAvailable: before unlock");
            this.lock.unlockRead(stamp);
        }
    }
}
