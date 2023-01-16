package appl;

import java.util.Comparator;

public class Application {

	public static void main(String[] args) {
		// without params
		Runnable runnable = new Runnable() {
			public void run() {
				// ...
			}
		};
		runnable.run();
		
		
		// with params
		Comparator<String> comparator = new Comparator<>() {
			@Override
			public int compare(String o1, String o2) {
				return 0;
			}
		};
		comparator.compare("a", "b");
	}
	
}
