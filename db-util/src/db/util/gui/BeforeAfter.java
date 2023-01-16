package db.util.gui;

public interface BeforeAfter {
	public abstract void before(String command);
	public abstract void after(String command);
	public abstract void after(String command, Throwable exception);
}
