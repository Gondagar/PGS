package pl.well.dao.exception;

/**
 * Created by serfer on 25.07.16.
 */
public class DaoSystemException extends DaoException {

    public DaoSystemException(String message) {
        super(message);
    }

    public DaoSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
