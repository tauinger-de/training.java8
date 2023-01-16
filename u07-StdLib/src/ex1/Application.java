package ex1;

import java.util.concurrent.ForkJoinPool;

public class Application {

	public static void main(String[] args) {
		final int N = 100;
		final int[] array = new int[N];
		for (int i = 0; i < N; i++)
			array[i] = i + 1;
		final NumberAddingTask task = new NumberAddingTask(array);		
		final ForkJoinPool pool = new ForkJoinPool();
		final int sum = pool.invoke(task);
		System.out.println(sum);
	}
}
