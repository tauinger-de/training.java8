package appl;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;

public class CharacterWriter implements Consumer<Character> {

	private final Writer writer;
	public CharacterWriter(Writer writer) {
		this.writer = writer;
	}
	
	public void accept(Character ch) {
		try {
			writer.write(ch);
			writer.flush();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
