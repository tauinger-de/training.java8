// http://www.javaspecialists.eu/archive/Issue215.html

package appl;

import static util.Util.XRunnable.xrun;
import static util.Util.tlog;


public class Application {
    public static void main(String[] args) {

        demoConcurrentWriteRead(new Account1(5000, 1000));
        demoConcurrentWriteRead(new Account2(5000, 1000));
        demoConcurrentWriteRead(new Account3(5000, 1000));
        demoConcurrentWriteRead(new Account4(5000, 1000));

        demoSequentialWriteRead(new Account2(5000, 1000));
        demoSequentialWriteRead(new Account4(5000, 1000));

        // demoConcurrentRead(new Account2(5000, 1000));
        demoConcurrentRead(new Account4(5000, 1000));

        // demoConcurrentWriteAndException(new Account2(5000, 1000));
        demoPositiveNegativeTest(new Account5(5000, 1000));
    }

    static void demoConcurrentWriteRead(Account account) {
        mlog(account.getClass().getSimpleName());
        Thread t1 = new Thread(() -> {
            tlog(">> deposit");
            account.withdraw(4000);
            tlog("<< deposit");
        });
        t1.start();
        xrun(() -> Thread.sleep(500));
        Thread t2 = new Thread(() -> {
            tlog(">> getAvailable");
            tlog("available = " + account.getAvailable());
            tlog("<< getAvailable");
        });
        t2.start();
        xrun(() -> t1.join());
        xrun(() -> t2.join());
    }

    static void demoSequentialWriteRead(Account account) {
        mlog(account.getClass().getSimpleName());
        Thread t1 = new Thread(() -> {
            tlog(">> deposit");
            account.withdraw(4000);
            tlog("<< deposit");
        });
        t1.start();
        xrun(() -> t1.join());
        Thread t2 = new Thread(() -> {
            tlog(">> getAvailable");
            tlog("available = " + account.getAvailable());
            tlog("<< getAvailable");
        });
        t2.start();
        xrun(() -> t2.join());
    }

    static void demoConcurrentRead(Account account) {
        mlog(account.getClass().getSimpleName());
        Thread t1 = new Thread(() -> {
            tlog(">> getAvailable");
            tlog("available = " + account.getAvailable());
            tlog("<< getAvailable");
        });
        t1.start();
        xrun(() -> Thread.sleep(500));
        Thread t2 = new Thread(() -> {
            tlog(">> getAvailable");
            tlog("available = " + account.getAvailable());
            tlog("<< getAvailable");
        });
        t2.start();
        xrun(() -> t2.join());
    }

    static void demoPositiveNegativeTest(Account account) {
        mlog(account.getClass().getSimpleName());
        Thread t1 = new Thread(() -> {
            tlog(">> deposit");
            account.withdraw(4000);
            tlog("<< deposit");
        });
        t1.start();
        xrun(() -> t1.join());
        Thread t2 = new Thread(() -> {
            try {
                tlog(">> deposit");
                account.withdraw(1000000);
                tlog("<< deposit");
            } catch (Exception e) {
                tlog("expected exception");
            }
        });
        t2.start();
        xrun(() -> t2.join());
    }
}
	
