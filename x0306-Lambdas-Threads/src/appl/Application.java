package appl;

import static util.Util.XRunnable.xrun;

public class Application {

	static void demo1() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Thread starts");
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println("Thread terminates");
			}
		});
		t.start();
		try {
			t.join();
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Thread terminated");
	}

	static void demo2() {
		Thread t = new Thread(() -> {
			System.out.println("Thread starts");
			xrun(() -> Thread.sleep(1000));
			System.out.println("Thread terminates");
		});
		t.start();
		xrun(() -> t.join());
		//xrun(t::join);
		
		//Button b = new Button();
		//b.addActionListener((e) -> System.out.println(e));
		//b.addActionListener(System.out::println);
		
		System.out.println("Thread terminated");
	}

	public static void main(String[] args) {
		demo1();
		demo2();
	}

}
