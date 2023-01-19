package appl;

@FunctionalInterface
public interface Tester<T> {

    /**
     * Testet, ob wir diesen Wert beachten wollen oder nicht.
     */
    boolean test(T value);

}
