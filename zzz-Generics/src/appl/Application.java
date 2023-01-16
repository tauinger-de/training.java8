package appl;

public class Application {

    public static void main(String[] args) {

        A a = new A(1);
        B b = new B(1, 2);
        C c = new C(1, 2, 3);

        A aa = c;


        Box<A> ba = new Box<>(a);
        Box<B> bb = new Box<>(b);
        Box<C> bc = new Box<>(c);


        demo1(a);
        demo1(b);
        demo1(c);

        demo2(ba);
        //demo2(bb);
        //demo2(bc);

        //demo3(ba);
        //demo3(bb);
        demo3(bc);

        demo4(ba);
        demo4(bb);
        demo4(bc);

        demo5(ba);
        demo5(bb);
        demo5(bc);
    }

    static void demo1(A a) {
        System.out.println(a.x);
    }

    static void demo2(Box<A> ba) {
        A a = ba.get();
        System.out.println(a.x);
        ba.set(new A(10));
    }

    static void demo3(Box<C> bc) {
        C c = bc.get();
        System.out.println(c.x + c.y + c.z);
        bc.set(new C(10, 20, 30));
    }


    static void demo4(Box<? extends A> ba) {
        ba.set(null);
        A a = ba.get();
        System.out.println(a.x);
    }

    static void demo5(Box<? super C> bc) {
        //bc.set(new A(10));
        bc.set(new C(10, 20, 30));
        Object o = bc.get();
        System.out.println(o);
    }
}
