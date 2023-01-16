package demo;

import util.SerializeUtil;

import java.io.Serializable;

import static java.lang.System.out;

public class Point implements Serializable {

    public static void main(String[] args) {
        Point p0 = new Point(1, 2);
        out.println(p0);
        Point p1 = SerializeUtil.serializeDeserialize(p0);
        out.println(p1);
        out.println(p0 == p1);
    }

    static class SerializedPoint implements Serializable {
        final private String s;

        SerializedPoint(Point point) {
            this.s = point.y + "#" + point.x;
        }

        private Object readResolve() {
            out.println("SerializedPoint.readResolve");
            out.println("\tthis = " + this);
            final String[] tokens = this.s.split("#");
            final Point p = new Point(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[0]));
            out.println("\treturning " + p);
            return p;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " [" + this.s + "]";
        }
    }

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private Object writeReplace() {
        out.println("Point.writeReplace");
        out.println("\tthis = " + this);
        final SerializedPoint sp = new SerializedPoint(this);
        out.println("\treturning " + sp);
        return sp;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + this.x + ", " + this.y + "]";
    }
}
