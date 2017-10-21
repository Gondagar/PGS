package pl.well.dao.exception;

/**
 * Created by serfer on 25.07.16.
 */
public class NoSuchPassException extends DaoBusinessException {


    public NoSuchPassException(String message) {
        super(message);

    }

    public NoSuchPassException(String message, Throwable cause) {
        super(message, cause);
    }
}
