package ai.prama.services.user.exceptions;

public class UserDatabaseException extends RuntimeException {

    public UserDatabaseException(String message) {
        super(message);
    }

    public UserDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
