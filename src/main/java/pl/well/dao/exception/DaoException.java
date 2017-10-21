package pl.well.dao.exception;

/**
 * Created by serfer on 25.07.16.
 */
public class DaoException extends Exception {

    public DaoException(String message) {
        super(message);
    }


    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
