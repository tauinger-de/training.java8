package ex2;

import java.io.StringReader;

public class Application {

    static class PrintingProcessor extends Processor {
        @Override
        protected void process(char ch) {
            System.out.print(ch + " ");
        }
    }

    static class CountingProcessor extends Processor {
        private int count = 0;

        @Override
        protected void begin() {
            this.count = 0;
        }

        @Override
        protected void process(char ch) {
            this.count++;
        }

        @Override
        protected void end() {
            System.out.println(this.count);
        }
    }

    public static void main(String[] args) {
        String input = "Reliefpfeiler";

        PrintingProcessor p1 = new PrintingProcessor();
        p1.run(new StringReader(input));

        System.out.println();

        CountingProcessor p2 = new CountingProcessor();
        p2.run(new StringReader(input));
    }
}
