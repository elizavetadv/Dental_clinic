package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static Connection connection;

    private final static String GET_USERS_QUERY ="SELECT * FROM user";
    private final static String ADD_USER_QUERY = "INSERT INTO users VALUE (0, ?, ?, ?)";

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
    public List<User> getUsers() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<User> users = new ArrayList<>();

        while(resultSet.next()){
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            users.add(user);
        }

        return users;
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
