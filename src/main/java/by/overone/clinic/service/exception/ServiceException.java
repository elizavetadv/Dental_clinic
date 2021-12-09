package by.overone.clinic.service.exception;

import by.overone.clinic.dao.exception.DAONotFoundException;

import java.sql.SQLException;

public class ServiceException extends Exception{
    public ServiceException() {
        super();
    }

    public ServiceException(String s, SQLException e) {
    }

    public ServiceException(String s, DAONotFoundException e) {
    }

    public ServiceException(DAONotFoundException e) {
    }
}
