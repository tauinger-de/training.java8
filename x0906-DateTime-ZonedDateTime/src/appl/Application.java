package appl;

import static java.lang.System.out;
import static util.Util.mlog;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Application {

	public static void main(String[] args) {
		demoZoneIds();
		demoClock();
		demoZonedDateTime();
	}

	static void demoZoneIds() {
		mlog();
		for (String s : ZoneId.getAvailableZoneIds()) {
			out.print(s + " ");
		}
		ZoneId zid1 = ZoneId.systemDefault();
		out.println(zid1);
		ZoneId zid2 = ZoneId.of("Europe/Berlin");
		out.println(zid1.equals(zid2));
	}

	static void demoClock() {
		mlog();
		Clock c1 = Clock.systemUTC();
		Clock c2 = Clock.systemDefaultZone();
		Clock c3 = Clock.system(ZoneId.systemDefault());
		out.println(c1);
		out.println(c1.millis());
		out.println(c2);
		out.println(c2.millis());
		out.println(c3);
		out.println(c3.millis());
		out.println(c1.equals(c2));
		out.println(c2.equals(c3));
		ZoneId zid = c2.getZone();
		System.out.println(zid);
	}

	static void demoZonedDateTime() {
		mlog();
		ZonedDateTime dt1 = ZonedDateTime.now();
		out.println(dt1);
		ZonedDateTime dt2 = ZonedDateTime.now(ZoneId.systemDefault());
		out.println(dt1.equals(dt2));
		LocalDateTime ldt = dt1.toLocalDateTime();
		out.println(ldt);
		out.println(ZonedDateTime.parse("2015-01-21T11:01:00.314+01:00[Europe/Berlin]"));
	}
}
