package s;

import static java.lang.System.out;
import static util.Util.mlog;
import util.Features;
import util.SerializeUtil;
import f.Foo;

public class Application {

	public static void main(String[] args) throws Exception {
		demoAnonymous();
		demoLambda();
	}

	static void demoAnonymous() {
		mlog();
		Foo f0 = new Foo() {
			public void run() {
				out.println("Hello");
			}
		};
		Features.print(f0.getClass());
		f0.run();
		Foo f1 = SerializeUtil.serializeDeserialize(f0);
		out.println(f1 == f0);
		f1.run();
	}
	static void demoLambda() {
		mlog();
		Foo f0 = () -> out.println("Hello");
		Features.print(f0.getClass());
		f0.run();
		Foo f1 = SerializeUtil.serializeDeserialize(f0);
		out.println(f1 == f0);
		f1.run();
	}
}
