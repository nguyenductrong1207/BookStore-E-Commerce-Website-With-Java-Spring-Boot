package project.bookstore.exception;

public class AuthorNotFoundException extends Throwable {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
