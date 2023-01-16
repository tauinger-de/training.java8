package sequential;

import static util.Util.mlog;
import static util.functional.proxies.TraceAroundAdvice.traceBiFunction;
import static util.functional.proxies.TraceAroundAdvice.traceConsumer;
import static util.functional.proxies.TraceAroundAdvice.traceFunction;
import static util.functional.proxies.TraceAroundAdvice.traceSupplier;
import static util.functional.proxies.TraceAroundAdvice.traceRunnable;
import static util.workviewer.Work.useViewer;

import util.Util.XRunnable;
import util.functional.proxies.TLog;
import util.workviewer.Work;

public class Application {

	static void call(XRunnable runnable) {
		useViewer();
		Work.viewer.start();
		TLog.start();
		XRunnable.xrun(runnable);
		Work.viewer.stop();
	}

	public static void main(String[] args) throws Exception {

		demoSFFFSimple();
		call(() -> demoSFFF());
		call(() -> demoSFFFC());
	}

	static void demoSFFFSimple() {
		mlog();
		Node<Integer> n1 = Node.supply(() -> 4);
		Node<Integer> n2 = n1.apply(x -> x + 1);
		Node<Integer> n3 = n1.apply(x -> x - 1);
		Node<Integer> n4 = n2.combine(n3, (x, y) -> x * y);
		Integer result = n4.get();
		System.out.println(result);
	}

	static void demoSFFF() {
		mlog();
		Node<Integer> n1 = Node.supply(traceSupplier("n1", 2000, () -> 4));
		Node<Integer> n2 = n1.apply(traceFunction("n2", 2000, x -> x + 1));
		Node<Integer> n3 = n1.apply(traceFunction("n3", 2000, x -> x - 1));
		Node<Integer> n4 = n2.combine(n3, traceBiFunction("n4", 2000, (x, y) -> x * y));
		Integer result = n4.get();
		System.out.println(result);
	}

	static void demoSFFFC() {
		mlog();
		Node<Integer> n1 = Node.supply(traceSupplier("n1", 2000, () -> 4));
		Node<Integer> n2 = n1.apply(traceFunction("n2", 2000, x -> x + 1));
		Node<Integer> n3 = n1.apply(traceFunction("n3", 2000, x -> x - 1));
		Node<Integer> n4 = n2.combine(n3, traceBiFunction("n4", 2000, (x, y) -> x * y));
		Node<Void> n5 = n4.accept(traceConsumer("n5", 2000, x -> System.out.println(x)));
		Void result = n5.get();
		System.out.println(result);
	}
}
