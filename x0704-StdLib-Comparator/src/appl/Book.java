package appl;

public class Book {

    private String isbn;
    private String title;
    private int price;

    public Book(String isbn, String title, int price) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + isbn + ", " + title + ", " + price + "]";
    }

}
