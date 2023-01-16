package ex2;

import java.io.Reader;


public abstract class Processor {

    public final void run(Reader reader) {
        try (final Reader r = reader) {
            this.begin();
            for (int ch = r.read(); ch != -1; ch = r.read()) {
                this.process((char) ch);
            }
            this.end();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void begin() {
    }

    protected abstract void process(char ch);

    protected void end() {
    }
}
