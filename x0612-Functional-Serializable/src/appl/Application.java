package appl;

import static util.Util.mlog;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Function;

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
		
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("zzz.dat"))) {
			out.writeObject(f);
		}
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("zzz.dat"))) {
			Function<String, Integer> ff = (Function<String, Integer>)in.readObject();
			int result = ff.apply("Hello");
			System.out.println(result);
		}
	}

	static void demoFunction() throws Exception {
		mlog();
		final int x = 37;
		Function<String, Integer> f = (Function<String, Integer> & Serializable) s -> x + s.length();
		
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("zzz.dat"))) {
			out.writeObject(f);
		}
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("zzz.dat"))) {
			Function<String, Integer> ff = (Function<String, Integer>)in.readObject();
			int result = ff.apply("Hello");
			System.out.println(result);
		}
	}
}
