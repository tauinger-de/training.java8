package util.functional.proxies;

import java.awt.*;
import java.util.function.Predicate;

public class PredicateProxy<T> extends Proxy<Predicate<T>> implements Predicate<T> {

    private final static Color color = new Color(255, 128, 128);

    public PredicateProxy(Predicate<T> predicate, AroundAdvice aroundAdvice) {
        super(predicate, aroundAdvice, color, "Predicate.test");
    }

    @Override
    public boolean test(T arg) {
        this.before(arg);
        boolean result = this.toInvoke.test(arg);
        this.after(result);
        return result;
    }
}
