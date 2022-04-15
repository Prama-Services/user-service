package ai.prama.services.user.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class ClientCausedUserDatabaseException extends RuntimeException {
    private static final long serialVersionUID = 3313430325588246592L;

    public ClientCausedUserDatabaseException(String message) {
        super(message);
    }

    public ClientCausedUserDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
