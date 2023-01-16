package appl;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static java.lang.System.out;
import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
        demoCreation();
        demoPlusMinus();
        demoAfterBefore();
        demoTruncated();
        demoCompareTo();
        demoParse();
    }

    static void demoCreation() {
        mlog();
        Instant d1 = Instant.ofEpochMilli(10);
        out.println(d1);
        Instant d2 = Instant.ofEpochSecond(10);
        out.println(d2);
        Instant d3 = Instant.ofEpochSecond(10, 20);
        out.println(d3);
        Instant d4 = Instant.now();
        out.println(d4);
    }

    static void demoPlusMinus() {
        mlog();
        Instant now = Instant.now();
        out.println(now);
        Instant d1 = now.plus(10, ChronoUnit.SECONDS);
        out.println(d1);
        Instant d2 = now.plus(10, ChronoUnit.MINUTES);
        out.println(d2);
        Instant d3 = now.minus(10, ChronoUnit.DAYS);
        out.println(d3);
    }

    static void demoAfterBefore() {
        mlog();
        Instant now = Instant.now();
        Instant later = now.plus(10, ChronoUnit.SECONDS);
        out.println(now.isAfter(later));
        out.println(now.isBefore(later));
    }

    static void demoTruncated() {
        mlog();
        Instant now = Instant.now();
        Instant result = now.truncatedTo(ChronoUnit.DAYS);
        out.println(result);
    }

    static void demoCompareTo() {
        mlog();
        Instant d1 = Instant.ofEpochSecond(10);
        Instant d2 = Instant.ofEpochSecond(20);
        out.println(d1.compareTo(d2));
        out.println(d2.compareTo(d1));
    }

    static void demoParse() {
        mlog();
        Instant d1 = Instant.parse("2015-01-20T13:10:05.429Z");
        out.println(d1);
        Instant d2 = Instant.parse("2015-01-20T13:10:05Z");
        out.println(d2);
        // weniger geht nicht...
        try {
            Instant.parse("2015-01-20T13:10Z");
        } catch (DateTimeParseException e) {
            out.println("Expected: " + e.getMessage());
        }

    }
}
