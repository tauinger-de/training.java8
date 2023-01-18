package util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class MethodExtractor {

    public static void main(String[] args) {
        extract(LocalDate.class);
    }

    private static void extract(Class<?> clazz) {
        out.printf("public %s %s {\n",
                clazz.isInterface() ? "interface" : clazz.isEnum() ? "enum" : "class",
                clazz.getSimpleName());
        for (Method m : clazz.getMethods()) {
            int mods = m.getModifiers();
            if (Modifier.isStatic(mods)) {
                String params = Arrays.stream(m.getParameters())
                        .map(p -> p.getType().getSimpleName() + " " + p.getName())
                        .collect(Collectors.joining(", "));
                out.printf("public static %s %s(%s) {}\n",
                        m.getReturnType().getSimpleName(),
                        m.getName(),
                        params
                );
            }
        }
        out.println("}");
    }
}
