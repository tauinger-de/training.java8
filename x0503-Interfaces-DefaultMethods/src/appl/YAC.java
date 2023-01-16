package appl;

public interface YAC<T> {  // Yet another Comparator...
	public abstract boolean eq(T v0, T v1);
	public abstract boolean gt(T v0, T v1);
	public default boolean ge(T v0, T v1) {
		return this.gt(v0, v1) || this.eq(v0, v1);  
	}
	public default boolean lt(T v0, T v1) {
		return ! this.ge(v0, v1);  
	}
	public default boolean le(T v0, T v1) {
		return this.eq(v0, v1) || this.lt(v0, v1);  
	}
}
