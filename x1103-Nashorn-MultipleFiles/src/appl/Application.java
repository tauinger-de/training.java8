package appl;

import javax.script.ScriptEngine;

import util.JSLoader;

public class Application {

	public static void main(String[] args) throws Exception {
		final ScriptEngine engine = JSLoader.load(
				"js/calculator.js", "js/pythagoras.js", "src/main.js"
		);
		engine.eval("main()");
	}
}
