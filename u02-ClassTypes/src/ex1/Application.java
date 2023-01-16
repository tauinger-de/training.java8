package ex1;

public class Application {

	public static void main(String[] args) {
		Runnable r = new CountingRunnable();
		Thread t = new Thread(r);
		t.start();
		try {
			t.join();
		}
		catch (InterruptedException e) {
		}
	}
}
