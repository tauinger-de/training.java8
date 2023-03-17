package util.functional.proxies;

import java.awt.*;
import java.util.function.Supplier;

public class SupplierProxy<R> extends Proxy<Supplier<R>> implements Supplier<R> {

    private final static Color color = new Color(255, 128, 128);

    public SupplierProxy(Supplier<R> supplier, AroundAdvice aroundAdvice) {
        super(supplier, aroundAdvice, color, "Supplier.get");
    }

    @Override
    public R get() {
        this.before();
        R result = this.toInvoke.get();
        this.after(result);
        return result;
    }

}
