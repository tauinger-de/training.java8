package appl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import util.TraceHandler;
import util.XProxy;

import static util.Util.mlog;

public class Application {
	public static void main(String[] args) {
		//demo();
		demoOld();
		//demoNew();
	}

	static void demo() {
		final MathService mathService = new MathServiceImpl();
		System.out.println(mathService.sum(40, 2));
		System.out.println(mathService.diff(40, 2));
	}

	static void demoOld() {
		mlog();
		final MathServiceImpl mathServiceImpl = new MathServiceImpl();
		InvocationHandler traceHandler = new TraceHandler(mathServiceImpl);
		// traceHandler = new TraceHandler(traceHandler);
		final MathService mathService = (MathService) Proxy.newProxyInstance(
				ClassLoader.getSystemClassLoader(), 
				new Class[] { MathService.class },
				traceHandler);

		System.out.println(mathService.sum(40, 2));
		System.out.println(mathService.diff(40, 2));
	}

	static void demoNew() {
		mlog();
		final MathService mathService = XProxy.create(
				MathService.class, 
				new MathServiceImpl(), 
				(m, a) -> System.out.println(">> " + m.getName() + " "	+ Arrays.toString(a)), 
				(m, a, r) -> System.out.println("<< " + m.getName() + " " + Arrays.toString(a) + " -> " + r),
				null);

		System.out.println(mathService.sum(40, 2));
		System.out.println(mathService.diff(40, 2));
	}
}
