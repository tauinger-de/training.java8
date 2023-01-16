package util;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import util.LambdaUtil;

public class From<T> extends Query<T> {
	public final Map<String, LambdaExpression> lambdaEntries;
	public final Class<T> tableClass;

	public From(Map<String, LambdaExpression> lambdaEntries, Class<T> tableClass) {
		this.lambdaEntries = lambdaEntries;
		this.tableClass = tableClass;
//		for (Map.Entry<String, LambdaEntry> e : lambdaEntries.entrySet())
//			System.out.println(e);
	}

	@SafeVarargs
	public final Select<T> select(SFunction<T, ?>... columns) {
		final List<LambdaExpression> columnLambdas = new ArrayList<>();
		for (SFunction<T, ?> column : columns) {
			final SerializedLambda l = LambdaUtil.getSerializedLambda(column);
			columnLambdas.add(this.lambdaEntries.get(l.getImplMethodName()));
		}
		return new Select<T>(this, Collections.unmodifiableList(columnLambdas));
	}

	public List<Object[]> list() {
		Field [] fields = this.tableClass.getFields();
		return createList(Query.database.getList(this.tableClass.getSimpleName()), fields);
	}

	protected List<Object[]> createList(List<?> selectedObjects, Field[] fields) {
		try {
			List<Object[]> rows = new ArrayList<>();
			for (Object obj : selectedObjects) {
				Object[] row = new Object[fields.length];
				for (int i = 0; i < fields.length; i++) {
					row[i] = fields[i].get(obj);
				}
				rows.add(row);
			}
			return rows;
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(" from ");
		buf.append(this.tableClass.getSimpleName());
		return buf.toString();
	}
	
	public From<T> log() {
		System.out.println(this);
		return this;
	}
}
