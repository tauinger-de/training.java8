package appl;

import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import static util.Util.mlog;

public class Application {

    /**
     * Diese Klasse ist ein "Doppel-Supplier" - warum nicht?
     */
    class F implements IntSupplier, DoubleSupplier {

        /**
         * Gibt den Preis in Cent zurück, also z.B. 199 für 1.99
         */
        public int getAsInt() {
            return (int) (Math.round(this.getAsDouble()) * 100);
        }

        public double getAsDouble() {
            return 12.99;
        }
    }

    /**
     * Ein neues Interface -- es erweitert den IntSupplier um einen Cast auf `char`.
     */
    public interface CharSupplier extends IntSupplier {
        default char getAsChar() {
            return (char) this.getAsInt();
        }
    }

    class Foo implements Supplier<Integer>, IntSupplier {
        public Integer get() {
            return getAsInt();      // wird automatisch auf Integer "geboxt" (so nennt Java das)
        }

        public int getAsInt() {
            return 42;
        }
    }

    public static void main(String[] args) {
        demoSupplier1();
        demoSupplier2();
        demoIntSupplier();
        demoIntRangeSupplier();
    }

    /**
     * Beispiel für Nutzung als anonyme Klasse
     */
    static void demoSupplier1() {
        mlog();
        Supplier<Integer> s = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 42;
            }
        };
        int v = s.get();
        System.out.println(v);
    }

    /**
     * Beispiel für Nutzung als Lambda
     */
    static void demoSupplier2() {
        mlog();
        Supplier<Integer> s = () -> 42;
        int v = s.get();
        System.out.println(v);
    }

    static void demoIntSupplier() {
        mlog();
        IntSupplier s = () -> 42;
        int v = s.getAsInt();
        System.out.println(v);
    }

    /**
     * Erzeugt einen Supplier, der aufsteigende Zahlen liefert > 0 -- und dann -1, wenn er "fertig"
     * ist
     */
    static void demoIntRangeSupplier() {
        mlog();
        IntSupplier s = new IntSupplier() {
            int n = 0;

            public int getAsInt() {
                return this.n >= 10 ? -1 : ++this.n;
            }
        };
        for (int v = s.getAsInt(); v >= 0; v = s.getAsInt()) {
            System.out.print(v + " ");
        }
        System.out.println();
    }
}
