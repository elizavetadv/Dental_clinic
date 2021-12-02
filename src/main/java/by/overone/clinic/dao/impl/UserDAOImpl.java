package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static Connection connection;

    private final static String GET_USERS_QUERY ="SELECT * FROM user";
    private final static String ADD_USER_QUERY = "INSERT INTO user VALUE (0, ?, ?, ?)";
    private final static String GET_USER_BY_ID_QUERY = "SELECT * FROM user WHERE user_id=?";
    private final static String GET_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email=?";

    private final static String DELETE_USER_QUERY = "DELETE FROM user WHERE user_id=?";

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
    public User getUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID_QUERY);
        preparedStatement.setLong(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();

        User user = new User();

        while(resultSet.next()) {
            user.setId(resultSet.getLong("user_id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_QUERY);
        preparedStatement.setString(1,email);

        ResultSet resultSet = preparedStatement.executeQuery();

        User user = new User();

        while(resultSet.next()) {
            user.setId(resultSet.getLong("user_id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
        }

        return user;
    }

    @Override
    public User addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_QUERY);

        preparedStatement.setString(1,user.getLogin());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3,user.getEmail());

        preparedStatement.executeUpdate();

        return user;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(long id) throws SQLException {
        User user = getUserById(id);

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);
        preparedStatement.setLong(1,id);

        preparedStatement.executeUpdate();

        return user;
    }
}
