package by.overone.clinic.service.exception;

import by.overone.clinic.dao.exception.DAODetailsExistException;

public class ServiceUserDetailsExistException extends Exception{
    public ServiceUserDetailsExistException() {
        super();
    }

    public ServiceUserDetailsExistException(String s, DAODetailsExistException e) {
    }

    public ServiceUserDetailsExistException(String s) {
    }
}
