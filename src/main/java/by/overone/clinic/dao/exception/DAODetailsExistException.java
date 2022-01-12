package by.overone.clinic.dao.exception;

public class DAODetailsExistException extends RuntimeException {
    public DAODetailsExistException(String errorCode) {
        super(errorCode);
    }
}
