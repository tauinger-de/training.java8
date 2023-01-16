package appl;

import java.io.*;
import java.util.function.Function;

import static util.Util.mlog;

@SuppressWarnings("unchecked")
public class Application {
    public static void main(String[] args) throws Exception {
        demoSerializableFunction();
        demoFunction();
    }

    static void demoSerializableFunction() throws Exception {
        mlog();
        final int x = 37;
        SerializableFunction<String, Integer> f = s -> x + s.length();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("zzz.dat"))) {
            out.writeObject(f);
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("zzz.dat"))) {
            Function<String, Integer> ff = (Function<String, Integer>) in.readObject();
            int result = ff.apply("Hello");
            System.out.println(result);
        }
    }

    static void demoFunction() throws Exception {
        mlog();
        final int x = 37;
        Function<String, Integer> f = (Function<String, Integer> & Serializable) s -> x + s.length();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("zzz.dat"))) {
            out.writeObject(f);
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("zzz.dat"))) {
            Function<String, Integer> ff = (Function<String, Integer>) in.readObject();
            int result = ff.apply("Hello");
            System.out.println(result);
        }
    }
}
