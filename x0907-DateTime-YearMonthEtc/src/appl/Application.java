package appl;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;

import static java.lang.System.out;
import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
        demoYearMonth();
        demoMonthDay();
        demoYear();
    }

    static void demoYearMonth() {
        mlog();
        YearMonth ym1 = YearMonth.of(2015, 1);
        YearMonth ym2 = YearMonth.of(2015, Month.JANUARY);
        YearMonth ym3 = YearMonth.now();
        out.println(ym1);
        out.println(ym1.equals(ym2));
        out.println(ym1 == ym2);
        out.println(ym3.getYear());
        out.println(ym3.getMonth());
        out.println(ym3.lengthOfMonth());
        out.println(ym3.lengthOfYear());
        out.println(ym3.isAfter(ym1));
        out.println(ym3.isBefore(ym1));
    }

    static void demoMonthDay() {
        mlog();
        MonthDay md1 = MonthDay.of(1, 20);
        MonthDay md2 = MonthDay.of(Month.JANUARY, 20);
        MonthDay md3 = MonthDay.now();
        out.println(md1);
        out.println(md1.equals(md2));
        out.println(md1 == md2);
        out.println(md3.getMonth());
        out.println(md3.getDayOfMonth());
        // ...
    }

    static void demoYear() {
        mlog();
        Year y1 = Year.of(2014);
        Year y2 = Year.of(2014);
        Year y3 = Year.now();
        out.println(y1);
        out.println(y1.equals(y2));
        out.println(y1 == y2);
        out.println(y3.getValue());
        out.println(y3.isLeap());
        out.println(y3.length());
        // ...
    }
}
