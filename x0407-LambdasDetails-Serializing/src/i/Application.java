package i;

import static java.lang.System.out;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import util.Features;
import util.SerializeUtil;
import f.Foo;
import static util.Util.mlog;

public class Application implements Serializable {

	//static Application THIS;
	
	public static void main(String[] args) throws Exception {
		Application appl = new Application();
		
//		appl.demoAnonymous();
//		appl.demoAnonymousTrick();
	//	appl.demoLambda();
//		
//		appl.demoSerializedLambda();
		appl.demoDeserializeSerializedLambda();
	}

	private Object writeReplace() {
		out.println(">> serializing " + this);
		return this;
	}
	private Object readResolve() {
		out.println("<< deserializing " + this);
		//return THIS;
		return this;
	}
	
	void demoAnonymous() throws Exception {
		mlog();
		Foo f0 = new Foo() {
			public void run() {
				out.println("Hello " + Application.this);
			}
		};
		Features.print(f0.getClass());
		f0.run();
		Foo f1 = SerializeUtil.serializeDeserialize(f0);
		out.println(f1 == f0);
		f1.run();
	}
	void demoAnonymousTrick() throws Exception {
		mlog();
		Foo f0 = new Foo() {
			public void run() {
				out.println("Hello " + Application.this);
			}
		};
		f0.run();
		Features.print(f0.getClass());
		Field f = f0.getClass().getDeclaredField("this$0");
		f.setAccessible(true);
		f.set(f0, null);
		Foo f1 = SerializeUtil.serializeDeserialize(f0);
		f.set(f1, this);
		out.println(f1 == f0);
		f1.run();
	}
	void demoLambda() {
		mlog();
		Foo f0 = () -> out.println("Hello " + this);
		Features.print(f0.getClass());
		f0.run();
		Foo f1 = SerializeUtil.serializeDeserialize(f0);
		out.println(f1 == f0);
		f1.run();
	}

	void demoSerializedLambda() throws Exception {
		mlog();
		Foo f0 = () -> out.println("Hello " + this);
		f0.run();
		Method writeReplaceMethod = f0.getClass().getDeclaredMethod("writeReplace");
		writeReplaceMethod.setAccessible(true);
		SerializedLambda sLambda = (SerializedLambda)writeReplaceMethod.invoke(f0);
		out.println(sLambda);
		Method readResolveMethod = sLambda.getClass().getDeclaredMethod("readResolve");
		readResolveMethod.setAccessible(true);
		Foo f1 = (Foo)readResolveMethod.invoke(sLambda);
		f1.run();
		out.println(f1 == f0);
	}
	
	void demoDeserializeSerializedLambda() throws Exception {
		mlog();
		Foo f0 = () -> out.println("Hello " + this);
		f0.run();
		Method writeReplaceMethod = f0.getClass().getDeclaredMethod("writeReplace");
		writeReplaceMethod.setAccessible(true);
		SerializedLambda lambda = (SerializedLambda)writeReplaceMethod.invoke(f0);
		Foo f1 = (Foo)SerializeUtil.deserialize(SerializeUtil.serialize(lambda));
		f1.run();
		out.println(f1 == f0);
	}
	
}
