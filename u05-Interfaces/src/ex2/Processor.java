package ex2;

public interface Processor {

    default void begin() {
    }

    void process(char ch);

    default void end() {
    }
}
