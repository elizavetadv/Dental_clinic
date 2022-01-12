package by.overone.clinic.service.exception;

import by.overone.clinic.dao.exception.DAOException;

import java.sql.SQLException;

public class ServiceException extends Exception{
    public ServiceException() {
        super();
    }

    public ServiceException(String s, SQLException e) {
    }

    public ServiceException(String s, DAOException e) {
    }

    public ServiceException(DAOException e) {
    }

    public ServiceException(String exception_in_updateClientDetails_in_service) {
    }
}
