package appl;

import util.InputStreamBasedIterator;
import util.ReaderBasedIterator;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static util.Util.mlog;

public class Application {

    static final String filename = "hello.txt";

    public static void main(String[] args) {
        demoStreamBuilder();
        demoBufferedReader();
        demoFilesLines();
        demoReaderBasedIterator();
        demoInputStreamBasedIterator();
    }

    static void demoStreamBuilder() {
        mlog();
        Stream.Builder<Integer> builder = Stream.builder();
        builder
                .add(42)
                .add(77)
                .build()
                .forEach(System.out::println);
    }

    static void demoBufferedReader() {
        mlog();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            reader.lines()
                    .map(line -> line.trim())
                    .filter(line -> line.startsWith("hello"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void demoFilesLines() {
        mlog();
        final Path path = FileSystems.getDefault().getPath(filename);
        try {
            Files.lines(path)
                    .map(line -> line.trim())
                    .filter(line -> line.startsWith("Hallo"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void demoReaderBasedIterator() {
        mlog();

        try (InputStream in = new FileInputStream(filename)) {
            Reader reader = new InputStreamReader(in);
            final ReaderBasedIterator iterator = new ReaderBasedIterator(reader);
            final Spliterator<Character> spliterator = Spliterators.spliteratorUnknownSize(iterator, 0);
            final Stream<Character> stream = StreamSupport.stream(spliterator, false);
            stream
                    .map(c -> Character.toLowerCase(c))
                    .forEach(System.out::print);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void demoInputStreamBasedIterator() {
        mlog();

        try (InputStream in = new FileInputStream(filename)) {
            final InputStreamBasedIterator iterator = new InputStreamBasedIterator(in);
            final Spliterator<Byte> spliterator = Spliterators.spliteratorUnknownSize(iterator, 0);
            final Stream<Byte> stream = StreamSupport.stream(spliterator, false);
            stream
                    .map(b -> (char) (byte) b)
                    .map(c -> Character.toUpperCase(c))
                    .forEach(System.out::print);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
