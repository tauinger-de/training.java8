package ex2;

import java.io.StringReader;

public class Application {
	static class PrintProcessor extends Processor {
		@Override
		protected void process(char ch) {
			System.out.print(ch);
		}
	}
	static class CharCountProcessor extends Processor {
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
		PrintProcessor p1 = new PrintProcessor();
		p1.run(new StringReader("hello"));
		System.out.println();

		CharCountProcessor p2 = new CharCountProcessor();
		p2.run(new StringReader("world"));
		System.out.println();
	}
}
