package util;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public abstract class Query<T> {
	public static <T> From<T> from(Class<T> tableClass) {
		String lambdaImplClass = findCallingClass();
		Map<String, LambdaExpression> entries = ClassAnalyser.run(lambdaImplClass);
		return new From<T>(entries, tableClass);
	}

	static String findCallingClass() {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			StackTraceElement e = elements[i];
			if (e.getClassName().equals(Query.class.getName()) && e.getMethodName().equals("from"))
				return elements[i + 1].getClassName();
		}
		throw new AssertionError();
	}
	public static Database database = new Database();

	public abstract List<Object[]> list();
	
	public List<Object[]> list(Connection con) {
		return Jdbc.executeQuery(con, this.toString());
	}
}
