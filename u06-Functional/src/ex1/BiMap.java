package ex1;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class BiMap<K0, K1, V> implements Iterable<K0> {

    public BiMap(BiFunction<K0, K1, V> valueCreator,
                 Map<K0, Map<K1, V>> map,
                 Supplier<Map<K1, V>> innerMapCreator) {
    }

    public BiMap(BiFunction<K0, K1, V> valueCreator) {
        this(valueCreator, new LinkedHashMap<K0, Map<K1, V>>(), () -> new LinkedHashMap<K1, V>());
    }

    public V get(K0 key0, K1 key1) {
        return null;
    }

    public Map<K1, V> get(K0 key0) {
        return null;
    }

    @Override
    public Iterator<K0> iterator() {
        return null;
    }
};
