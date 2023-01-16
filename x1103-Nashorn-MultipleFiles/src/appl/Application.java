package appl;

import util.JSLoader;

import javax.script.ScriptEngine;

public class Application {

    public static void main(String[] args) throws Exception {
        final ScriptEngine engine = JSLoader.load(
                "js/calculator.js", "js/pythagoras.js", "src/main.js"
        );
        engine.eval("main()");
    }
}
