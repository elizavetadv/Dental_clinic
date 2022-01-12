package by.overone.clinic.dao.exception;

public class DAODetailsNotFoundException extends RuntimeException{
    public DAODetailsNotFoundException(String errorCode) {
        super(errorCode);
    }
}
