package appl;

import java.time.temporal.ChronoUnit;

import static java.lang.System.out;
import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
        demoChronoUnit();
    }

    static void demoChronoUnit() {
        mlog();
        for (ChronoUnit u : ChronoUnit.values()) {
            out.println(u);
        }
        out.println(ChronoUnit.SECONDS.compareTo(ChronoUnit.YEARS));
        out.println(ChronoUnit.SECONDS.compareTo(ChronoUnit.DAYS));
        out.println(ChronoUnit.SECONDS.compareTo(ChronoUnit.MINUTES));
    }
}
