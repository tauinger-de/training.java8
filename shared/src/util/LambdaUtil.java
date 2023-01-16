package util;

// http://stackoverflow.com/questions/21887358/reflection-type-inference-on-java-8-lambdas

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

public class LambdaUtil {
	
	public static Method getMethod(Object functionalIntefaceObject) {
		return getLambdaMethod(getSerializedLambda(functionalIntefaceObject));
	}
	
	public static SerializedLambda getSerializedLambda(Object function) {
		if (! function.getClass().isSynthetic())
			throw new IllegalArgumentException();
		if (function == null || !(function instanceof java.io.Serializable)) {
			throw new IllegalArgumentException();
		}
		for (Class<?> cls = function.getClass(); cls != null; cls = cls.getSuperclass()) {
			try {
				final Method replaceMethod = cls.getDeclaredMethod("writeReplace");
				replaceMethod.setAccessible(true);
				final Object serializedForm = replaceMethod.invoke(function);
				if (serializedForm instanceof SerializedLambda) {
					return (SerializedLambda) serializedForm;
				}
			}
			catch (NoSuchMethodError e) {
				continue;
			}
			catch (Throwable t) {
				throw new RuntimeException("Error while extracting serialized lambda", t);
			}
		}
		throw new RuntimeException("writeReplace method not found");
	}

	public static Method getLambdaMethod(SerializedLambda lambda) {
		try {
			final String implClassName = lambda.getImplClass().replace('/', '.');
			final Class<?> implClass = Class.forName(implClassName);

			final String lambdaName = lambda.getImplMethodName();

			for (final Method m : implClass.getDeclaredMethods()) {
				if (m.getName().equals(lambdaName)) {
					return m;
				}
			}
			throw new Exception("Lambda Method not found");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
