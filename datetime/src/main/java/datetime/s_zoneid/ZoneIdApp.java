package datetime.s_zoneid;

import java.time.ZoneId;

import static java.lang.System.out;
import static util.Util.mlog;

public class ZoneIdApp {

    public static void main(String[] args) {
        demoZoneIds();
    }

    static void demoZoneIds() {
        mlog();

        out.println("Available ZoneIds:");
        ZoneId.getAvailableZoneIds().forEach(s -> out.println("  - " + s));
        out.println();

        ZoneId zid1 = ZoneId.systemDefault();
        out.println(zid1);

        ZoneId zid2 = ZoneId.of("Europe/Berlin");
        out.println(zid1.equals(zid2));
    }
}
