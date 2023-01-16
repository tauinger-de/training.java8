package appl;

import static java.lang.System.out;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import static util.Util.mlog;

public class Application {

	// javac -parameters

	public static void main(String[] args) {
		demoInspectOld(Foo.class);
		demoInspectOld(Object.class);
		demoInspectNew(Foo.class);		
		demoInspectNew(Object.class);		
	}

	static void demoInspectOld(Class<?> cls) {
		mlog(cls);
		for (Method m : cls.getDeclaredMethods()) {
			out.print(m.getReturnType().getSimpleName() + " ");
			out.print(m.getName() + "(");
			Class<?>[] types = m.getParameterTypes();
			for (int i = 0; i < types.length; i++) {
				if (i > 0)
					out.print(", ");
				out.print(types[i].getSimpleName() + " p" + i);
			}
			out.println(")");
		}
	}

	static void demoInspectNew(Class<?> cls) {
		mlog(cls);
		for (Method m : cls.getDeclaredMethods()) {
			out.print(m.getReturnType().getSimpleName() + " ");
			out.print(m.getName() + "(");
			Parameter[] parameters = m.getParameters();
			for (int i = 0; i < parameters.length; i++) {
				Parameter p = parameters[i];
				if (i > 0)
					out.print(", ");
				out.print(p.getType().getSimpleName() + " " + p.getName());
			}
			out.println(")");
		}

	}
}
