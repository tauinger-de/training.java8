package ex2;

import static ex2.Value.$;

public class Application {
	public static void main(String[] args) {
		Value v = $(42);
		System.out.println(v.eval());
	}
}
