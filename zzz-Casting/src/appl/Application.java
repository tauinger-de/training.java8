package appl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.RandomAccess;

import util.Features;
import util.LambdaUtil;

@FunctionalInterface
interface Foo {
	Foo f();
}

@FunctionalInterface
interface Bar {
	Bar b();
}

@FunctionalInterface
interface Hello<T> {
	void h(T value);
}

class FooBar implements Foo, Bar {

	@Override
	public Foo f() {
		System.out.println("FooBar.f");
		return this;
	}
	
	@Override
	public Bar b() {
		System.out.println("FooBar.b");
		return this;
	}

}

public class Application {
	public static void main(String[] args) {
		
		FooBar fooBar = new FooBar();
		// ((Foo & Bar)fooImpl).f();   // Error
		
		((Foo & Bar)fooBar).f();
		((Foo & Bar)fooBar).b();
		
		//Bar obj = (Foo & Bar) fooBar;
		
		

//		LinkedList<String> list = new LinkedList<>();
//		List<String> list1 = (List & Queue) list; //OK
//		List<String> list2 = (List & RandomAccess) list; //ClassCastException	
		
		Object obj = (Hello<String>)(s) -> System.out.println(s);
		
		Hello<Double> h = (Hello<Double> & Serializable) (s) -> System.out.println(s);
		Features.print(Application.class);
		Features.print(h.getClass());
//		Class<?> actualTypeParameter = LambdaUtil.getMethod(h).getParameterTypes()[0];
//		System.out.println(actualTypeParameter);
	}
	static void demo(Object obj) {
		Foo f = (Foo & Bar)obj;
		Bar b = (Bar)f;
		
	}
	
	
}
