package project.bookstore.exception;

public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
