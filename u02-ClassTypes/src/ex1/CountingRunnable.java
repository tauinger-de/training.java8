package ex1;

public class CountingRunnable implements Runnable {
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1000);
				System.out.print(i + " ");
			}
			catch (InterruptedException e) {
			}
		}
	}
}
