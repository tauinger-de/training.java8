package appl;

public class Application {

    public static void main(String[] args) {

        Worker squareIt = d -> d * d;

        Worker incrementIt = d -> d + 1;

        Worker printIt = d -> {
            System.out.println(d);
            return d;
        };

        // simpler Aufruf eines Workers
        int result = squareIt.workOn(8);
        System.out.println(result);

        // dies hier ist "fluent programming":
        squareIt.andThen(incrementIt).andThen(printIt).workOn(5);
        incrementIt.andThen(incrementIt).andThen(squareIt).andThen(printIt).workOn(0);
    }
}
