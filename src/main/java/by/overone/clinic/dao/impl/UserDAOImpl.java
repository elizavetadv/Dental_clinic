package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static Connection connection;

    static {
        try {
            String URL = "jdbc:mysql://localhost:3306/veterinary_clinic";
            String dbUser = "root";
            String password = "root";

            connection = DriverManager.getConnection(URL, dbUser, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(long id) {
        return null;
    }
}
