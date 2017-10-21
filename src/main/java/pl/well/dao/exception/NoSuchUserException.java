package pl.well.dao.exception;

/**
 * Created by serfer on 25.07.16.
 */
public class NoSuchUserException extends DaoBusinessException {


    public NoSuchUserException(String message) {
        super(message);

    }

    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
