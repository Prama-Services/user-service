package ai.prama.services.user.exceptions;

public class UserDatabaseException extends RuntimeException {
    private static final long serialVersionUID = 1746313772919956164L;

    public UserDatabaseException(String message) {
        super(message);
    }

    public UserDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
