package ex1;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberAddingTask extends RecursiveTask<Integer> {
	
	public static AtomicInteger addCount = new AtomicInteger();

	final int[] array;
	final int start; 
	final int end;
	
    public NumberAddingTask(int[] array) {
    	this(array, 0, array.length);
    }
    
    private NumberAddingTask(int[] array, int start, int end) {
    	this.array = array;
    	this.start = start;
    	this.end = end;
    }

    @Override
    protected Integer compute() {
    	final int result;
    	if (this.end - this.start <= 10) {
    		int sum = 0;
    		for (int i = this.start; i < this.end; i++) { 
    			sum += this.array[i];
    			addCount.incrementAndGet();
    		}
    		result = sum;
    	}
    	else {
            final int center = (this.end + this.start) / 2;
            final NumberAddingTask t0 = new NumberAddingTask(this.array, this.start, center);
            final NumberAddingTask t1 = new NumberAddingTask(this.array, center, this.end);
            result = t0.invoke() + t1.invoke();
        }
		return result;
    }
}
