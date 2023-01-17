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
            out.printf("- %s (%d Sekunden)\n", u.name(), u.getDuration().get(ChronoUnit.SECONDS));
        }

        ChronoUnit chronoUnit1 = ChronoUnit.MICROS;
        ChronoUnit chronoUnit2 = ChronoUnit.MILLIS;
        out.printf("Is %s less than %s? %b", chronoUnit1.name(), chronoUnit2.name(),
                chronoUnit1.compareTo(chronoUnit2) < 0);
    }
}
