package ex1.a;

@SuppressWarnings({"Convert2Lambda", "CatchMayIgnoreException"})
public class Application {

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            public void run() {
                // System.out.println(Application.this); // geht nicht, kein Zugriff auf äußere Klasse
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.print(i + " ");
                    } catch (InterruptedException e) {
                    }
                }
            }
        };

        Thread t = new Thread(r);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
        }
    }
}
