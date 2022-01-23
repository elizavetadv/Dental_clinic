package by.overone.clinic.dao.exception;

public class DAOUserExistException extends RuntimeException{
    public DAOUserExistException(String errorCode) {
        super(errorCode);
    }

    public DAOUserExistException() {

    }
}
