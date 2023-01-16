package appl;

import java.util.function.Supplier;

abstract class Parser {
	public abstract Parser get();
	public abstract boolean canParse();
}

class RealParser extends Parser {
	public Parser get() {
		return this;
	}
	public boolean canParse() {
		System.out.println("RealParser.canParse");
		return true;
	}
}

class LazyParser extends Parser {
	Supplier<Parser> parserSupplier;
	Parser parser;
	LazyParser(Supplier<Parser> parserSupplier) {
		this.parserSupplier = parserSupplier;
	}
	public Parser get() {
		if (parser == null)
			parser = parserSupplier.get();
		return parser;
	}
	public boolean canParse() {
		// a real impl.
		return get().canParse();
	}
}


public class Application {
	public static void main(String[] args) {
		Parser p1 = new RealParser();
		Parser p2 = new LazyParser(() -> new RealParser());
		p1.canParse();
		p2.canParse();
		
	}
}
