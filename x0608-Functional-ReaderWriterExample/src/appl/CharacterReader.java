package appl;

import java.io.Reader;
import java.util.function.Supplier;

public class CharacterReader implements Supplier<Character> {

    private final Reader reader;

    public CharacterReader(Reader reader) {
        this.reader = reader;
    }

    public Character get() {
        try {
            int ch = reader.read();
            return ch < 0 ? null : (char) ch;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
