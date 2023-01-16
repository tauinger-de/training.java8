package scanner;

public class ScannerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final long errorColumn;
	private final long errorLine;
	private final Symbol symbol;

	public ScannerException(Scanner s, String message) {
		super(message);
		this.errorLine = s.getLine();
		this.errorColumn = s.getColumn();
		this.symbol = s.current();
	}

	public long getErrorLine() {
		return this.errorLine;
	}

	public long getErrorColumn() {
		return this.errorColumn;
	}

	public String toString() {
		return this.getClass().getName() + " [" + "found " + this.symbol + ", line " + this.errorLine + ", column " + this.errorColumn + ": "
				+ this.getMessage() + "]";
	}
}