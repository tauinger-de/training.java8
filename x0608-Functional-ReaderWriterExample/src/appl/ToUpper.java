package appl;

import java.util.function.Function;

public class ToUpper implements Function<Character, Character> {

    public Character apply(Character ch) {
        return Character.toUpperCase(ch);
    }

}
