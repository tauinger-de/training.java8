package ex1.c;

@SuppressWarnings({"AnonymousHasLambdaAlternative", "CatchMayIgnoreException"})
public class Application {

    public static void main(String[] args) {
        new Application().foo();
    }

    private void foo() {
        Thread t = new Thread() {
            public void run() {
                System.out.println(Application.this);
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.print(i + " ");
                    } catch (InterruptedException e) {
                    }
                }
            }
        };

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
        }
    }
}
