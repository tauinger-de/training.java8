package scanner;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Specials implements Iterable<Special> {
    private final Map<String, Special> map = new LinkedHashMap<>();

    public Special create(String text) {
        Special special = this.map.get(text);
        if (special == null) {
            special = new Special(text);
            this.map.put(text, special);
        }
        return special;
    }

    public Iterator<Special> iterator() {
        return this.map.values().iterator();
    }
}
