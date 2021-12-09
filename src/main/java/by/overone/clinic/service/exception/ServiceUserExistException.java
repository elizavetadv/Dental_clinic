package by.overone.clinic.service.exception;

public class ServiceUserExistException extends Exception{
    public ServiceUserExistException() {
        super();
    }

    public ServiceUserExistException(String user_already_exist) {
    }
}
