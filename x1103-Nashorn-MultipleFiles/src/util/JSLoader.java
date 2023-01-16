package util;

import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JSLoader {
	public static ScriptEngine load(String... files) {
		final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		for (String file : files) {
			try {
				engine.eval(new FileReader(file));
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return engine;
	}
}
