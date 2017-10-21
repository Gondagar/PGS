package pl.well.dao.exception;

/**
 * Created by serfer on 25.07.16.
 */
public class EntityExistsException extends DaoBusinessException {


    public EntityExistsException(String message) {
        super(message);

    }

    public EntityExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
