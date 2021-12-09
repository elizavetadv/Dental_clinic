package by.overone.clinic.dao.exception;

public class DAOUserExistException extends Exception{
    public DAOUserExistException() {
        super();
    }

    public DAOUserExistException(String message) {
        super(message);
    }
}
