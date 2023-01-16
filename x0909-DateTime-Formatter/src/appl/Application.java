package appl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class Application {

    public static void main(String[] args) {
        demoDate(FormatStyle.SHORT);
        demoDate(FormatStyle.LONG);
        demoTime(FormatStyle.SHORT);
        demoTime(FormatStyle.MEDIUM);
        demoDateTime(FormatStyle.SHORT);
        demoDateTime(FormatStyle.MEDIUM);
    }

    static void demoDate(FormatStyle style) {
        mlog(style);
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedDate(style);
        String s = f.format(LocalDateTime.now());
        System.out.println(s);
        TemporalAccessor ta = f.parse(s);
        int day = ta.get(ChronoField.DAY_OF_MONTH);
        int month = ta.get(ChronoField.MONTH_OF_YEAR);
        int year = ta.get(ChronoField.YEAR);
        System.out.println(day + " " + month + " " + year);
    }

    static void demoTime(FormatStyle style) {
        mlog(style);
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime(style);
        String s = f.format(LocalDateTime.now());
        System.out.println(s);
        TemporalAccessor ta = f.parse(s);
        int hour = ta.get(ChronoField.HOUR_OF_DAY);
        int minute = ta.get(ChronoField.MINUTE_OF_HOUR);
        int second = ta.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println(hour + " " + minute + " " + second);
    }

    static void demoDateTime(FormatStyle style) {
        mlog(style);
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(style);
        String s = f.format(LocalDateTime.now());
        System.out.println(s);
        TemporalAccessor ta = f.parse(s);
        int day = ta.get(ChronoField.DAY_OF_MONTH);
        int month = ta.get(ChronoField.MONTH_OF_YEAR);
        int year = ta.get(ChronoField.YEAR);
        int hour = ta.get(ChronoField.HOUR_OF_DAY);
        int minute = ta.get(ChronoField.MINUTE_OF_HOUR);
        int second = ta.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println(day + " " + month + " " + year + " " + hour + " " + minute + " " + second);
    }
}
