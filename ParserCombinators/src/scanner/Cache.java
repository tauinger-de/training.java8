package scanner;

import java.util.HashMap;
import java.util.Map;

public abstract class Cache<K,V> {

	private final Map<K,V> map = new HashMap<K,V>();
	
	public V lookup(K key) {
		if (key == null)
			throw new RuntimeException("key mustn't be null");
		V v = map.get(key);
		if (v == null) {
			v = createValue(key);
			if (v == null)
				throw new RuntimeException("value mustn't be null");
			map.put(key, v);
		}
		return v;
	}
	public abstract V createValue(K key);
}
