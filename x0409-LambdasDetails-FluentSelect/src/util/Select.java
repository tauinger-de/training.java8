package util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class Select<T> extends Query<T> {
	public final From<T> from;
	public final List<LambdaExpression> columnsLambdas;

	public Select(From<T> from, List<LambdaExpression> columnsLambdas) {
		this.from = from;
		this.columnsLambdas = columnsLambdas;
	}

	public Where<T> where(SPredicate<T> predicate) {
		return new Where<T>(this, predicate);
	}

	@Override
	public List<Object[]> list() {
		return createList(Query.database.getList(this.from.tableClass.getSimpleName()), this.from.tableClass);
	}

	protected List<Object[]> createList(List<?> selectedObjects, final Class<?> tableClass) {
		try {
			final List<LambdaExpression> columns = this.columnsLambdas;
			final int n = columns.size();
			final Field[] fields = new Field[n];
			for (int i = 0; i < n; i++) 
				fields[i] = tableClass.getField(columns.get(i).fieldName);
			return this.from.createList(selectedObjects, fields);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("select ");
		buf.append(this.columnsLambdas.stream().map(l -> l.fieldName).collect(Collectors.joining(", ")));
		buf.append(this.from);
		return buf.toString();
	}

	public Select<T> log() {
		System.out.println(this);
		return this;
	}
}
