package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
	private Map<String, List<?>> map = new HashMap<>();
	public void add(String name, List<?> list) {
		this.map.put(name, list);
	}
	public List<?> getList(String name) {
		return this.map.get(name);
	}
}
