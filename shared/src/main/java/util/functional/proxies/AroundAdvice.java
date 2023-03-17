package util.functional.proxies;

public abstract class AroundAdvice {

    protected Proxy<?> proxy;

    public void before(Object... args) {
    }

    public void after(Object result) {
    }

    static public final AroundAdvice emptyAdvice = new AroundAdvice() {
    };
}
