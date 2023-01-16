package appl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class Client {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 8000)) {
            final ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            final ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            final String author = "Wirth";
            final int year = 1970;
            final Predicate<Book> predicate = (Predicate<Book> & Serializable)
                    book -> book.author.equals(author) && book.year >= year;
            out.writeObject(predicate);
            List<Book> books = (List<Book>) in.readObject();
            books.forEach(System.out::println);
        }
    }
}
