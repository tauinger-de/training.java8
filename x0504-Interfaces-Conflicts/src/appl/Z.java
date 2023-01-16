package appl;

public interface Z {
	public default void run() {
		f();
		while(! done()) {
			g();
		}
		h();
	}
	public abstract void f();
	public abstract void g();
	public abstract void h();
	public abstract boolean done();
}
