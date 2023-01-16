package appl;

import static util.Util.mlog;

import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Application {

	class F implements IntSupplier, DoubleSupplier {
		public int getAsInt() { return 42; }
		public double getAsDouble() { return 2.71; }
	}
	
	public interface CharSupplier extends IntSupplier {
		public default char getAsChar() {
			return (char) this.getAsInt();
		}
	}

	class Foo implements Supplier<Integer>, IntSupplier {
		public Integer get() {
			return 42;
		}

		public int getAsInt() {
			return 42;
		}
	}

	public static void main(String[] args) {
		demoSupplier1();
//		demoSupplier2();
//		demoIntSupplier1();
//		demoIntSupplier2();
//		demoIntRangeSupplier();
	}

	static void demoSupplier1() {
		mlog();
		Supplier<Integer> s = new Supplier<Integer>() {
			@Override
			public Integer get() {
				return 42;
			}
		};
		int v = s.get();
		System.out.println(v);
	}

	static void demoSupplier2() {
		mlog();
		Supplier<Integer> s = () -> 42;  // target typing 
		int v = s.get();
		System.out.println(v);
	}

	static void demoIntSupplier1() {
		mlog();
		IntSupplier s = new IntSupplier() {
			public int getAsInt() {
				return 42;
			}
		};
		int v = s.getAsInt();
		System.out.println(v);
	}

	static void demoIntSupplier2() {
		mlog();
		IntSupplier s = () -> 42;
		int v = s.getAsInt();
		System.out.println(v);
	}

	static void demoIntRangeSupplier() {
		mlog();
		IntSupplier s = new IntSupplier() {
			int n = 0;

			public int getAsInt() {
				return this.n == 10 ? 0 : ++this.n;
			}
		};
		for (int v = s.getAsInt(); v != 0; v = s.getAsInt()) {
			System.out.print(v + " ");
		}
		System.out.println();
	}

	static int n = 0;
	static void demoIntRangeSupplier2() {
		mlog();
		IntSupplier s = () -> n == 10 ? 0 : ++n;
		for (int v = s.getAsInt(); v != 0; v = s.getAsInt())
			System.out.print(v + " ");
		System.out.println();
	}
}
