package datetime.r_zoneddatetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.lang.System.out;
import static util.Util.mlog;

public class ZonedDateTimeApp {

    public static void main(String[] args) {
        demoCreate();
    }

    static void demoCreate() {
        mlog();

        ZonedDateTime dt1 = ZonedDateTime.now();
        out.println(dt1);

        LocalDateTime ldt = dt1.toLocalDateTime();
        out.println(ldt);

        ZonedDateTime dt2 = ZonedDateTime.now(ZoneId.systemDefault());
        out.println(dt1.equals(dt2));

        ZonedDateTime dt3 = ZonedDateTime.of(2023, 3, 17, 13, 1, 0, 0, ZoneId.of("Australia/Melbourne"));
        out.println(dt3);

        out.println(
                ZonedDateTime.parse("2015-01-21T11:01:00.314+01:00[Europe/Berlin]")
        );
    }

}
