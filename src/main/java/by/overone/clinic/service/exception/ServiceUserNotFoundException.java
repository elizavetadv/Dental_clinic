package by.overone.clinic.service.exception;

import by.overone.clinic.dao.exception.DAOUserNotFoundException;

public class ServiceUserNotFoundException extends Exception{
    public ServiceUserNotFoundException() {
        super();
    }

    public ServiceUserNotFoundException(String user_is_not_founded) {
    }

    public ServiceUserNotFoundException(String s, DAOUserNotFoundException e) {
    }
}
