package by.overone.clinic.dao.exception;

public class DAOUserNotFoundException extends RuntimeException{

    public DAOUserNotFoundException(String errorCode) {
        super(errorCode);
    }

    public DAOUserNotFoundException() {

    }
}
