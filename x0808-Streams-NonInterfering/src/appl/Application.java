package appl;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static util.Util.mlog;

public class Application {

    public static void main(String[] args) {
        demoProblem();
        demoOkay();
    }

    static void demoProblem() {
        mlog();
        try {
            List<Point> points = new ArrayList<>();
            points.add(new Point(1, 1));
            points.add(new Point(2, 2));
            points.stream()
                    .forEach(point -> points.add(new Point(0, 0)));
        } catch (Exception e) {
            System.out.println("Expected: " + e);
        }
    }

    static void demoOkay() {
        mlog();
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.stream()
                .forEach(point -> point.x += 1);
        points.stream()
                .forEach(point -> out.println(point));
    }

}
