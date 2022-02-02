package by.overone.clinic.dao.exception;

public class DAORecordNotFoundException extends RuntimeException{
    public DAORecordNotFoundException(String errorCode) {
        super(errorCode);
    }
}
