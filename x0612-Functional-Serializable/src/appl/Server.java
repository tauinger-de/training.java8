package appl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class Server {

    static final Book[] books = new Book[]{
            new Book("1111", "Pascal", "Wirth", 1960),
            new Book("2222", "Modula", "Wirth", 1970),
            new Book("3333", "Oberon", "Wirth", 1980),
            new Book("4444", "Eiffel", "Meyer", 1990),
    };

    public static void main(String[] args) throws Exception {
        try (final ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Waiting...");
            try (final Socket socket = serverSocket.accept()) {
                final ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                final ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                final Object request = in.readObject();
                final Predicate<Book> predicate = (Predicate<Book>) request;
                final List<Book> response = new ArrayList<>();
                for (Book book : books) {
                    if (predicate.test(book))
                        response.add(book);
                }
                System.out.println("Sending " + response);
                out.writeObject(response);
            }
        }
    }
}
