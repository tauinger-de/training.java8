package appl;

@SuppressWarnings({"unused", "Convert2MethodRef"})
public class Application {

    /**
     * Demonstriert Nutzung einer statischen Methode als Referenz
     */
    static void demoRunnable_staticMethod() {
        // hier rufen wir einfach nur eine andere Methode auf
        Runnable runnable1 = () -> Foo.doSomething();

        // dafür können wir auch eine Methoden-Referenz nehmen
        Runnable runnable2 = Foo::doSomething;
    }

    /**
     * Demonstriert Nutzung einer Instanz-Methode als Referenz
     */
    static void demoRunnable_instanceMethod() {
        Bar bar = new Bar(42);

        // hier rufen wir einfach nur eine andere Methode auf
        Runnable runnable1 = () -> bar.dumpValue();

        // dafür können wir auch eine Methoden-Referenz nehmen
        Runnable runnable2 = bar::dumpValue;
    }

    static void demoConstructorReference() {
        // klassisch
        Action<Integer> instantiateBarAction1 = v -> new Bar(v);

        // mit Constructor-Ref
        Action<Integer> instantiateBarAction2 = Bar::new;
        instantiateBarAction2.execute(1);
    }


    public static void main(String[] args) {
        demoRunnable_staticMethod();
        demoRunnable_instanceMethod();
        demoConstructorReference();
    }
}
