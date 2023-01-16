package scanner;

import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;

public class Scanner {

	private Reader reader;
	private char currentChar;
	private StringBuilder buffer = new StringBuilder();
	private Symbol currentSymbol = null;
	private boolean eof;

	private HashMap<String, Special> operators = new HashMap<String, Special>();
	private HashMap<String, Special> words = new HashMap<String, Special>();

	private int column;
	private int line;

	private static final Special comment = new Special("//");
	private static final Special undefined = new Special("");

	private void registerWord(Special special) {
		final String text = special.getAsText();
		if (this.words.get(text) != null)
			throw new RuntimeException(special + " already registered");
		this.words.put(text, special);
	}

	private void registerOperator(Special special) {
		final String text = special.getAsText();
		for (int i = 1; i < text.length(); i++) {
			String t = text.substring(0, i);
			if (this.operators.get(t) == null) {
				this.operators.put(t, undefined);
			}
		}
		Special s = this.operators.get(text);
		if (s != null && s != undefined)
			throw new RuntimeException(special + " already registered");
		this.operators.put(text, special);
	}

	public Scanner(Specials specials, Reader reader) {
		this.registerOperator(comment);
		if (specials != null) {
			for (Special special : specials) {
				if (Character.isJavaIdentifierStart(special.getAsText().charAt(0)))
					this.registerWord(special);
				else
					this.registerOperator(special);
			}
		}
		this.reader = reader;
		this.column = 0;
		this.line = 0;
		this.read();
	}

	public Symbol next() {
		Symbol sym = null;
		try {
			while (true) {
				this.skipWhitespace();
				sym = this.nextToken();
				if (sym != comment)
					break;
				this.skipTo("\n");
			}
		}
		catch (ScannerException e) {
			this.read();
			throw e;
		}
		return this.currentSymbol = sym;
	}

	public Symbol current() {
		return this.currentSymbol;
	}

	public boolean currentIsOneOf(Special... symbols) {
		for (Special sym : symbols) {
			if (this.current() == sym)
				return true;
		}
		return false;
	}

	public Special readSpecial(Special expectedSymbol) {
		if (this.current() != expectedSymbol)
			throw new ScannerException(this, expectedSymbol + " expected");
		this.next();
		return expectedSymbol;
	}

	public Special readSpecial(Special... expectedSymbols) {
		for (Special sym : expectedSymbols) {
			if (this.current() == sym) {
				this.next();
				return sym;
			}
		}
		throw new ScannerException(this, Arrays.toString(expectedSymbols) + " expected");
	}

	public Identifier readIdentifier() {
		if (!this.current().isIdentifier())
			throw new ScannerException(this, "identifier expected");
		Identifier sym = this.current().asIdentifier();
		this.next();
		return sym;
	}

	public Literal readLiteral() {
		if (!this.current().isLiteral())
			throw new ScannerException(this, "literal expected");
		Literal sym = this.current().asLiteral();
		this.next();
		return sym;
	}

	private Symbol nextToken() {
		if (this.eof)
			return null;
		if (Character.isLetter(this.currentChar)) {
			Symbol sym = scanIdentifier();
			Symbol word = this.words.get(sym.getAsText());
			if (word != null)
				sym = word;
			return sym;
		}
		if (this.currentChar == '\\') {
			// return reserved word as identifier!
			this.mustRead();
			Symbol sym = scanIdentifier();
			Symbol word = this.words.get(sym.getAsText());
			if (word == null)
				throw new ScannerException(this, "bad usage of \\\\");
			return sym;
		}
		if (Character.isDigit(this.currentChar))
			return scanNumber();
		if (this.currentChar == '\"')
			return scanString();
		if (this.currentChar == '\'')
			return scanChar();
		return scanOperator();
	}

	private void mustRead() {
		this.read();
		if (this.eof)
			throw new ScannerException(this, "unexpected EOF");
	}

	private void read() {
		try {
			int i = this.reader.read();
			this.currentChar = (i == -1 ? 0 : (char) i);
			if (this.currentChar == '\n') {
				this.line++;
				this.column = 1;
			}
			else {
				this.column++;
			}
		}
		catch (Exception e) {
			this.currentChar = 0;
		}
		this.eof = this.currentChar == 0;
	}

	private void skipWhitespace() {
		while (Character.isWhitespace(this.currentChar))
			this.read();
	}

	private Symbol scanIdentifier() {
		this.buffer.setLength(0);
		this.buffer.append(this.currentChar);
		this.read();
		while (!eof
				&& (this.currentChar == '_' || Character.isDigit(this.currentChar) || Character
						.isLetter(this.currentChar))) {
			this.buffer.append(this.currentChar);
			this.read();
		}
		String s = this.buffer.toString();
		if (s.equals("true"))
			return Literal.get(true);
		if (s.equals("false"))
			return Literal.get(false);
		return Identifier.get(s);
	}

	private Symbol scanNumber() {
		this.buffer.setLength(0);
		this.buffer.append(this.currentChar);
		this.read();
		while (Character.isDigit(this.currentChar)) {
			this.buffer.append(this.currentChar);
			this.read();
		}
		if (this.currentChar != '.') {
			if (Character.isLetter(this.currentChar))
				throw new ScannerException(this, "digit expected");
			return Literal.get(Integer.parseInt(this.buffer.toString()));
		}
		this.buffer.append(this.currentChar);
		this.mustRead();
		if (!Character.isDigit(this.currentChar))
			throw new ScannerException(this, "Digit expected");
		while (Character.isDigit(this.currentChar)) {
			this.buffer.append(this.currentChar);
			this.read();
		}
		if (Character.isLetter(this.currentChar))
			throw new ScannerException(this, "digit expected");
		return Literal.get(Double.parseDouble(this.buffer.toString()));
	}

	private Symbol scanString() {
		this.buffer.setLength(0);
		this.mustRead();
		while (this.currentChar != '\"') {
			if (this.currentChar == '\n')
				throw new ScannerException(this, "newline in string");
			if (this.currentChar == '\\')
				this.buffer.append(scanEscapeChar());
			else
				this.buffer.append(this.currentChar);
			this.mustRead();
		}
		this.read();
		return Literal.get(this.buffer.toString());
	}

	private Symbol scanChar() {
		this.mustRead();
		char ch = this.currentChar;
		if (ch == '\\')
			ch = scanEscapeChar();
		this.mustRead();
		if (this.currentChar != '\'')
			throw new ScannerException(this, "' expected");
		this.read();
		return Literal.get(ch);
	}

	private char scanEscapeChar() {
		this.mustRead();
		switch (this.currentChar) {
		case '\\':
			return '\\';
		case '\'':
			return '\'';
		case '\"':
			return '\"';
		case 'n':
			return '\n';
		case 't':
			return '\t';
		case 'f':
			return '\f';
		case 'b':
			return '\b';
		default:
			throw new ScannerException(this, "bad escape-char: " + this.currentChar);
		}
	}

	private Symbol scanOperator() {
		String s = String.valueOf(this.currentChar);
		Symbol symbol = this.operators.get(s);
		while (symbol != null) {
			this.read();
			s += this.currentChar;
			Symbol sym = this.operators.get(s);
			if (sym == undefined)
				continue;
			if (sym == null)
				break;
			symbol = sym;
		}
		if (symbol == null)
			throw new ScannerException(this, "bad character " + this.currentChar);
		return symbol;
	}

	private boolean skipTo(String s) {
		if (this.eof)
			return false;
		boolean found = false;
		while (!found) {
			char ch = s.charAt(0);
			while (this.currentChar != ch) {
				this.read();
				if (this.eof)
					return false;
			}
			found = true;
			for (int i = 1; i < s.length(); i++) {
				this.read();
				if (this.eof)
					return false;
				if (this.currentChar != s.charAt(i)) {
					found = false;
					break;
				}
			}
		}
		this.read();
		return true;
	}

	public int getLine() {
		return this.line + 1;
	}

	public int getColumn() {
		return this.column - 1;
	}
}
