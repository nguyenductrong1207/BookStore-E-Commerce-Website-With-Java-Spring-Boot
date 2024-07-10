package project.bookstore.exception;

public class CategoryNotFoundException extends Throwable {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
