package ex2;

import java.io.StringReader;

public class Application {

    static class PrintingProcessor implements Processor {
        @Override
        public void process(char ch) {
            System.out.print(ch + " ");
        }
    }

    static class CountingProcessor implements Processor {
        private int count = 0;

        @Override
        public void begin() {
            this.count = 0;
        }

        @Override
        public void process(char ch) {
            this.count++;
        }

        @Override
        public void end() {
            System.out.println(this.count);
        }
    }

    public static void main(String[] args) {
        String input = "Reliefpfeiler";

        ProcessorRunner runner1 = new ProcessorRunner(new PrintingProcessor());
        runner1.run(new StringReader(input));

        System.out.println();

        ProcessorRunner runner2 = new ProcessorRunner(new CountingProcessor());
        runner2.run(new StringReader(input));
    }
}
