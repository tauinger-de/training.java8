package appl;

public class Application {

	public static void main(String[] args) {
		Box<A> ba = new Box<>(new A(1));
		Box<B> bb = new Box<>(new B(1, 2));
		Box<C> bc = new Box<>(new C(1, 2, 3));
		
		extendsA(ba);
		extendsA(bb);
		extendsA(bc);
		
		superC(ba);
		superC(bb);
		superC(bc);

		// extendsB(ba);
		extendsB(bb);
		extendsB(bc);
		
		superB(ba);
		superB(bb);
		// superB(bc);

		//extendsC(ba);
		//extendsC(bb);
		extendsC(bc);
		
		superA(ba);
		//superA(bb);
		//superA(bc);
		
		tuWas(ba);
		//tuWas(bb);
		//tuWas(bc);
	}
	
	static void tuWas(Box<A> box) {
		
	}
	static void extendsA(Box<? extends A> box) {
		A a = box.get();
		System.out.println(a);
		box.set(null);
		//box.set(new A(10));   // illegal
		//box.set(new B(10,20));
		//box.set(new C(10,20,30));
	}
	static void superC(Box<? super C> box) {
		Object o = box.get();
		System.out.println(o);
		//box.set(new A(1));
		//box.set(new B(1, 2));
		box.set(new C(1, 2, 3));
	}
	static void extendsB(Box<? extends B> box) {
		B b = box.get();
		System.out.println(b);
//		box.set(new A(10));   // illegal
//		box.set(new B(10,20));
//		box.set(new C(10,20,30));
		box.set(null);
	}
	static void superB(Box<? super B> box) {
		Object o = box.get();
		System.out.println(o);
		//box.set(new A(1));
		box.set(new B(1, 2));
		box.set(new C(1, 2, 3));
	}
	static void extendsC(Box<? extends C> box) {
		C c = box.get();
		System.out.println(c);
		box.set(null);
	}
	static void superA(Box<? super A> box) {
		Object o = box.get();
		System.out.println(o);
		box.set(new A(1));
		box.set(new B(1, 2));
		box.set(new C(1, 2, 3));
	}
}
