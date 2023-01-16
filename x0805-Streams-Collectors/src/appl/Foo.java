package appl;

import java.util.ArrayList;
import java.util.List;

public class Foo {
	
	public final int key1;
	public final int key2;
	public final String data;
	
	public Foo(int key1, int key2, String data) {
		this.key1 = key1;
		this.key2 = key2;
		this.data = data;
	}

	@Override
	public String toString() {
		return "Foo [" + key1 + ", " + key2 + ", " + data + "]";
	}
	
	public static List<Foo> foos = new ArrayList<>();
	static {
		foos.add(new Foo(1000, 10, "aaa"));
		foos.add(new Foo(1000, 10, "bbb"));
		foos.add(new Foo(1000, 20, "ccc"));
		foos.add(new Foo(1000, 20, "ddd"));
		foos.add(new Foo(2000, 10, "eee"));
		foos.add(new Foo(2000, 10, "fff"));
		foos.add(new Foo(2000, 20, "ggg"));
		foos.add(new Foo(2000, 20, "hhh"));
	}
}

