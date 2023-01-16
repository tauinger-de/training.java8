package appl;

import static java.lang.System.out;


public class Application {
	public static void main(String[] args) {
		Foo foo = new FooImpl();
//		foo.f();
		foo.g();

//		YAC<Integer> yac = new YAC<Integer>() {
//			public boolean eq(Integer v0, Integer v1) {
//				return v0.equals(v1);
//			}
//			public boolean gt(Integer v0, Integer v1) {
//				return v0.compareTo(v1) > 0;
//			}
//		};
//		out.println(yac.eq(1, 1));
//		out.println(yac.gt(2, 1));
//		out.println(yac.ge(1, 1));
//		out.println(yac.ge(2, 1));
//		out.println(yac.lt(1, 2));
//		out.println(yac.le(1, 2));
//		out.println(yac.le(1, 1));
	}		
}
