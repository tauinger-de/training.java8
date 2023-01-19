package appl;

/**
 * Ableitung einer anonymen Klasse aus einer lokalen
 */
@SuppressWarnings("Convert2Lambda")
public class ComparisonLocalToAnonymousClass {

    public static void main(String[] args) {
        // --- mit LOKALER Klasse ---
        // 1) Deklaration
        class MyRunnable implements Runnable {
            @Override
            public void run() {
                System.out.println("Hello!");
            }
        }
        // 2) Instanziierung
        Runnable r = new MyRunnable();
        // 3) Ausführung
        r.run();


        // --- mit ANONYMER Klasse ---
        // 1) Deklaration und 2) Instanziierung in einem
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello again!");
            }
        };
        // 3) Ausführung
        r2.run();
    }
}
