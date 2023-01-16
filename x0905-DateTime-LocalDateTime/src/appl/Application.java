package appl;

import static java.lang.System.out;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import static util.Util.mlog;

public class Application {

	public static void main(String[] args) {
		demoLocalDate();
		demoLocalTime();
		demoLocalDateTime();
		demoLocalDateTimeToInstant();
	}

	static void demoLocalDate() {
		mlog();
		LocalDate d1 = LocalDate.of(2015, 1, 20);
		LocalDate d2 = LocalDate.of(2015, Month.JANUARY, 20);
		LocalDate d3 = LocalDate.now();
		out.println(d3);
		out.println(d1.equals(d2));
		out.println(d1);
		out.println(d1.getDayOfMonth());
		out.println(d1.getDayOfWeek());
		out.println(d1.getDayOfYear());
		out.println(d1.getMonth());
		out.println(d1.getYear());
		LocalDate d4 = LocalDate.parse("2015-01-20");
		out.println(d4);
	}

	static void demoLocalTime() {
		mlog();
		LocalTime t1 = LocalTime.of(12, 30);
		LocalTime t2 = LocalTime.of(12, 30, 5);
		LocalTime t3 = LocalTime.of(12, 30, 5, 500 * 1000_000);
		LocalTime t4 = LocalTime.now();
		out.println(t1);
		out.println(t2);
		out.println(t3);
		out.println(t4);
		out.println(t3.getHour());
		out.println(t3.getMinute());
		out.println(t3.getSecond());
		out.println(t3.getNano());
		LocalTime t5 = LocalTime.parse("12:30:05");
		out.println(t5);
		LocalTime t6 = LocalTime.parse("12:30");
		out.println(t6);
	}

	static void demoLocalDateTime() {
		mlog();
		LocalDate d = LocalDate.of(2015, 1, 20);
		LocalTime t = LocalTime.of(12, 30, 5);
		LocalDateTime dt = LocalDateTime.of(d, t);
		out.println(dt);
		out.println(dt.getYear());
		// ...
		out.println(dt.getSecond());
		out.println(dt.getNano());
		LocalDate d1 = dt.toLocalDate();
		out.println(d == d1);
		LocalTime t1 = dt.toLocalTime();
		out.println(t == t1);
		out.println(LocalDateTime.parse("2015-01-20T12:30:05"));
	}

	static void demoLocalDateTimeToInstant() {
		mlog();
		Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
		out.println(instant);
		// LocalDateTime dt = LocalDateTime.from(instant); // throws Exception
	}
}
