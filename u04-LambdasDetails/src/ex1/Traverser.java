package ex1;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import util.LambdaUtil;

public class Traverser {
	@SuppressWarnings("unchecked")
	public static <T extends Component> void traverse(Component component, Handler<T> handler) {
		final Class<?> cls;
		if (handler.getClass().isSynthetic()) {
			final Method m = LambdaUtil.getMethod(handler);
			cls = m.getParameterTypes()[0];
		}
		else {
			final ParameterizedType pt = (ParameterizedType)handler.getClass().getGenericInterfaces()[0];
			cls = (Class<?>)pt.getActualTypeArguments()[0];
		}
		if (cls.isAssignableFrom(component.getClass()))
			handler.handle((T)component);
		if (component instanceof Container) {
			final Container container = (Container)component;
			for (int i = 0; i < container.getComponentCount(); i++) {
				traverse(container.getComponent(i), handler);
			}
		}
	}
}
