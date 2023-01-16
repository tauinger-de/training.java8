package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InputStreamBasedIterator implements Iterator<Byte> {

	private final InputStream in;
	private int currentByte;

	public InputStreamBasedIterator(InputStream in) {
		this.in = in;
		this.read();
	}

	@Override
	public boolean hasNext() {
		return this.currentByte >= 0;
	}

	@Override
	public Byte next() {
		if (!this.hasNext())
			throw new NoSuchElementException();
		byte b = (byte) this.currentByte;
		this.read();
		return b;
	}

	private void read() {
		try {
			this.currentByte = in.read();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

