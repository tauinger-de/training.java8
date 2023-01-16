package util;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReaderBasedIterator implements Iterator<Character> {

	private final Reader reader;
	private int currentChar;

	public ReaderBasedIterator(Reader reader) {
		this.reader = reader;
		this.read();
	}

	@Override
	public boolean hasNext() {
		return this.currentChar >= 0;
	}

	@Override
	public Character next() {
		if (!this.hasNext())
			throw new NoSuchElementException();
		char c = (char) this.currentChar;
		this.read();
		return c;
	}

	private void read() {
		try {
			this.currentChar = reader.read();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

