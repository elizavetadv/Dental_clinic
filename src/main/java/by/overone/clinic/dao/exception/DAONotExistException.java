package by.overone.clinic.dao.exception;

public class DAONotExistException extends RuntimeException {
    public DAONotExistException(String message) {
        super(message);
    }

}
