package ex2;

import java.io.Reader;

public class ProcessorRunner {

    private final Processor processor;

    public ProcessorRunner(Processor processor) {
        this.processor = processor;
    }

    public final void run(Reader reader) {
        try (final Reader r = reader) {
            this.processor.begin();
            for (int ch = r.read(); ch != -1; ch = r.read()) {
                this.processor.process((char) ch);
            }
            this.processor.end();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
