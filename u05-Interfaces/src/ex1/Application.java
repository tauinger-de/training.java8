package ex1;

public class Application {
    public static void main(String[] args) {
        DynamicArray<String> array = new DynamicArray<>(String.class, 2);
        array.add("one");
        array.add("two");
        array.add("three");
        array.add("four");
        array.add("five");
        array.add("six");
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + " ");
        }
        System.out.println();

        // benutze neu implementierte interface Iterable
        array.forEach(e -> System.out.print(e + " > "));
        System.out.println();
        array.iterator().forEachRemaining(System.out::println);
    }
}
