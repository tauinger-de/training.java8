package example3;

import parser.util.Value;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Context {
    private final Map<String, Value> variables = new HashMap<>();

    public final PrintStream ps;

    public Context() {
        this(System.out);
    }

    public Context(PrintStream ps) {
        this.ps = ps;
    }

    public void setVariable(String name, Value value) {
        this.variables.put(name, value);
    }

    public Value getVariable(String name) {
        return this.variables.get(name);
    }
}
