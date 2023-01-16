package ex1;

import java.util.Map;

public class Application {

	static class Foo {
		public final int i;
		public final String s;
		public final String data;
		public Foo(int i, String s, String data) {
			this.i = i;
			this.s = s;
			this.data = data;
		}
		@Override
		public String toString() {
			return "Foo [i=" + i + ", s=" + s + ", data=" + data + "]";
		}
	}
	
	public static void main(String[] args) {
		final BiMap<Integer, String, Foo> map = 
				new BiMap<>((i, s) -> new Foo(i, s, i + ", " + s.toUpperCase()));
		
		Foo f1 = map.get(1, "one");
		System.out.println(f1);			// Foo [i=1, s=one, data=1, ONE]
		
		Foo f2 = map.get(1, "one");
		System.out.println(f1 == f2);  	// true
		
		map.get(1, "two");
		map.get(5, "red");
		map.get(5, "blue");
		map.get(5, "green");
		
		Map<String, Foo> m1 = map.get(1);
		for(Map.Entry<String,Foo> e : m1.entrySet())
			System.out.println(e.getKey() + " ==> " + e.getValue());
		// one ==> Foo [i=1, s=one, data=1, ONE]
		// two ==> Foo [i=1, s=two, data=1, TWO]		
		
		Map<String, Foo> m2 = map.get(5);
		for(Map.Entry<String,Foo> e : m2.entrySet())
			System.out.println(e.getKey() + " ==> " + e.getValue());
		// red ==> Foo [i=2, s=red, data=2, RED]
		// blue ==> Foo [i=2, s=blue, data=2, BLUE]
		// green ==> Foo [i=2, s=green, data=2, GREEN]
		
		System.out.println();
		for (Integer i : map) {
			Map<String, Foo> m = map.get(i);
			for(Map.Entry<String,Foo> e : m.entrySet())
				System.out.println(e.getKey() + " ==> " + e.getValue());
		}
		// one ==> Foo [i=1, s=one, data=1, ONE]
		// two ==> Foo [i=1, s=two, data=1, TWO]
		// red ==> Foo [i=5, s=red, data=5, RED]
		// blue ==> Foo [i=5, s=blue, data=5, BLUE]
		// green ==> Foo [i=5, s=green, data=5, GREEN]		
	}
}
