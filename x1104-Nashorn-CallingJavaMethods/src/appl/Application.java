package appl;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Application {

	public static void main(String[] args) throws Exception {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
		System.out.println(engine);
		engine.eval(new FileReader("src/calculator.js"));
		Invocable invocable = (Invocable) engine;
		
		int sum = (int)invocable.invokeFunction("plus", 40, 2);
		System.out.println(sum);

		Calculator calculator  = new Calculator();
		int diff = (int)invocable.invokeFunction("minus", calculator , 40, 2);
		System.out.println(diff);

		int product = (int)invocable.invokeFunction("times", 40, 2);
		System.out.println(product);
	}
}
