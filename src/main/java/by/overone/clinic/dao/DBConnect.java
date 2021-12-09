package by.overone.clinic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private final static String URL = "jdbc:mysql://localhost:3306/veterinary_clinic";
    private final static String dbUser = "root";
    private final static String password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, dbUser, password);
    }
}
