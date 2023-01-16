// http://www.oracle.com/technetwork/articles/java/jf14-nashorn-2126515.html
// http://docs.oracle.com/javase/8/docs/technotes/guides/scripting/nashorn/api.html
// http://winterbe.com/posts/2014/04/05/java8-nashorn-tutorial/


package appl;

import static util.Util.mlog;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Application {

	public static void main(String[] args) throws Exception {
		demoCreateEngine();
		demoEvalString();
		demoEvalStringReader();
		demoEvalFileReader();
	}
	
	static void demoCreateEngine() throws ScriptException {
		mlog();
		ScriptEngine engine1 = new ScriptEngineManager().getEngineByName("nashorn");
		ScriptEngine engine2 = new ScriptEngineManager().getEngineByName("JavaScript");
		System.out.println(engine1.getClass().getName());
		System.out.println(engine2.getClass().getName());
		System.out.println(engine1 == engine2);
	}

	static void demoEvalString() throws ScriptException {
		mlog();
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval("print('Hello World!');");
	}

	static void demoEvalStringReader() throws ScriptException {
		mlog();
		Reader reader = new StringReader("print('Hello World!')");
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(reader);
	}

	static void demoEvalFileReader() throws ScriptException, FileNotFoundException {
		mlog();
		Reader reader = new FileReader("src/hello.js");
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(reader);
	}
}
