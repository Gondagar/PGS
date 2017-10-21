package pl.well.dao.exception;

/**
 * Created by serfer on 25.07.16.
 */
public class DaoBusinessException extends DaoException {

    public DaoBusinessException(String message) {
        super(message);
    }

    public DaoBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
