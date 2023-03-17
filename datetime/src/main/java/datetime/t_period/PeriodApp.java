package datetime.t_period;

import java.time.Period;

import static java.lang.System.out;
import static util.Util.mlog;

public class PeriodApp {

    public static void main(String[] args) {
        demoCreation();
        demoGetMethods();
        demoPlusMinus();
        demoParse();
    }

    static void demoCreation() {
        mlog();
        Period p1 = Period.ofYears(1).withMonths(6).withDays(15);
        Period p2 = Period.ofWeeks(52);
        Period p3 = Period.of(4, 8, 12);
        out.println(p1);
        out.println(p2);
        out.println(p3);
    }

    static void demoGetMethods() {
        mlog();
        Period p = Period.ofYears(1).withMonths(6).withDays(15);
        out.println(p.getYears());
        out.println(p.getMonths());
        out.println(p.getDays());
    }

    static void demoPlusMinus() {
        mlog();
        Period p = Period.ofYears(1).withMonths(6).withDays(15);
        out.println(p);
        p = p.plusMonths(10);
        p = p.plusDays(10);
        p = p.minusDays(3);
        out.println(p);
    }

    static void demoParse() {
        mlog();
        Period p = Period.parse("P1Y6M15D");
        out.println(p);
    }
}
