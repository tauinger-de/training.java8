package appl;

import util.Features;
import util.LambdaUtil;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static java.lang.System.out;
import static util.Util.mlog;

public class Application {

    public static void main(String[] args) throws Exception {
        analyseAnonymous();
//		analyseLambda();
        analyseSerializedLambda();
//		demoLambdaUtil();
    }

    static void analyseAnonymous() throws Exception {
        Foo<String> f = new Foo<String>() {
            public void run(String value) {
                out.println(value);
            }
        };
        f.run("Hello");
        Class<?> cls = f.getClass();
        Features.print(cls);
        // Method runMethodString = cls.getMethod("run", String.class);
        // Method runMethodObject = cls.getMethod("run", Object.class);
        // runMethodString.invoke(f, "red");
        // runMethodObject.invoke(f, "green");
        // // runMethodObject.invoke(f, 42); // runtime error

//		Class<?> typeParam = null;
//		for (Method m : cls.getDeclaredMethods()) {
//			if (m.getName().equals("run")) {
//				Class<?> argType = m.getParameterTypes()[0];
//				if (argType != Object.class) {
//					typeParam = argType;
//					break;
//				}
//			}
//		}
//		out.println("====> " + typeParam);

        out.println("getGenericInterfaces...");
        Type[] ifaces = cls.getGenericInterfaces();
        for (Type iface : ifaces)
            out.println("\t" + iface);
        Type iface = ifaces[0];
        System.out.println(iface);
        System.out.println(iface.getClass());
        ParameterizedType pt = (ParameterizedType) iface;

        out.println("getActualTypeArguments...");
        Type[] argTypes = pt.getActualTypeArguments();
        for (Type argType : argTypes)
            out.println("\t" + argType);
        Class<?> argClass = (Class<?>) argTypes[0];
        out.println(argClass);
    }

    static void analyseLambda() throws Exception {
        mlog();
        Foo<String> f = value -> out.println(value);
        // Foo<String> f = (String value) -> out.println(value);
        f.run("Hello");
        Class<?> cls = f.getClass();
        Features.print(cls);
        // Method runMethodString = cls.getMethod("run", String.class); //
        // runtime error...
//		Method runMethodString = cls.getMethod("run", Object.class);
//		runMethodString.invoke(f, "World");
        // runMethodString.invoke(f, 10); // runtime error ...

        out.println("getGenericInterfaces...");
        Type[] ifaces = cls.getGenericInterfaces();
        for (Type iface : ifaces)
            out.println("\t" + iface);
        Type iface = ifaces[0];
        System.out.println(iface);
//		System.out.println(iface.getClass());
        // ParameterizedType pt = (ParameterizedType)iface; // runtime error
    }

    @SuppressWarnings("rawtypes")
    static void analyseSerializedLambda() throws Exception {
        mlog();
        Foo<String> f0 = value -> out.println(value);
        Class<? extends Foo> cls0 = f0.getClass();

        Method writeReplaceMethod = cls0.getDeclaredMethod("writeReplace");
        writeReplaceMethod.setAccessible(true);
        SerializedLambda sLambda = (SerializedLambda) writeReplaceMethod.invoke(f0);

        final String implClassName = sLambda.getImplClass().replace('/', '.');
        final Class<?> implClass = Class.forName(implClassName);
        Features.print(implClass);
        System.out.println(sLambda);
        final String methodName = sLambda.getImplMethodName();
        out.println(methodName);
        Method method = null;
        for (final Method m : implClass.getDeclaredMethods()) {
            if (m.getName().equals(methodName)) {
                method = m;
                break;
            }
        }
        Class<?> argType = method.getParameterTypes()[0];
        out.println(argType);
    }

    static void demoLambdaUtil() throws Exception {
        mlog();
        Foo<String> f0 = value -> out.println(value);

        Method m = LambdaUtil.getMethod(f0);
        Class<?>[] argTypes = m.getParameterTypes();
        for (Class<?> argType : argTypes) {
            out.println("\t" + argType);
        }
        Class<?> argType = argTypes[0];
        out.println(argType);
    }
}
