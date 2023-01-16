package appl;

public class Application {

    public static void main(String[] args) {
        Worker squaringWorker = d -> d * d;
        Worker printingWorker = d -> {
            System.out.println(d);
            return d;
        };

        squaringWorker.andThen(printingWorker).work(5);
    }
}
