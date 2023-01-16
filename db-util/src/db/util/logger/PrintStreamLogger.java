package db.util.logger;

import java.io.PrintStream;

public class PrintStreamLogger implements Logger {
	private final PrintStream out;
	public PrintStreamLogger(PrintStream out) {
		this.out = out;
	}
	public PrintStreamLogger() {
		this(System.out);
	}
	public final void log(String message) {
		this.out.println(message);
	}
}
