package by.overone.clinic.dao.exception;

import java.sql.SQLException;

public class DAONotFoundException extends Exception{
    public DAONotFoundException(String message) {
        super(message);
    }

    public DAONotFoundException(String user_not_added, SQLException e) {
    }
}
