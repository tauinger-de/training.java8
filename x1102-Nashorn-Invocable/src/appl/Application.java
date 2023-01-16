package appl;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

public class Application {

    public static void main(String[] args) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("src/plus.js"));
        Invocable invocable = (Invocable) engine;
        int sum = (int) invocable.invokeFunction("plus", 40, 2);
        System.out.println(sum);
    }
}
