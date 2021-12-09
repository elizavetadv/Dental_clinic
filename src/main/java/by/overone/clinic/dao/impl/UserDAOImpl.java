package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.DBConnect;
import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.exception.DAONotFoundException;
import by.overone.clinic.dao.exception.DAOUserExistException;
import by.overone.clinic.dao.exception.UserNotFoundException;
import by.overone.clinic.model.Role;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;
import by.overone.clinic.util.constant.UserConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final static String GET_USERS_QUERY = "SELECT * FROM user";
    private final static String ADD_USER_QUERY = "INSERT INTO user VALUE (0, ?, ?, ?, ?)";
    private final static String GET_USER_BY_ID_QUERY = "SELECT * FROM user WHERE user_id=?";
    private final static String GET_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email=?";
    private final static String GET_USER_BY_LOGIN_QUERY = "SELECT * FROM user WHERE login=?";
    //private final static String UPDATE_USER_QUERY = "UPDATE user SET login=?, password=?, email=? WHERE user_id=?";
    //private final static String DELETE_USER_QUERY = "DELETE FROM user WHERE user_id=?";
    private final static String GET_USER_DETAILS_QUERY = "SELECT * FROM user_details WHERE user_user_id=?";
    public static final String ADD_USER_DETAILS_ID_QUERY = "INSERT INTO user_details (user_user_id) VALUE(?)";
    public static final String UPDATE_USER_DETAILS_QUERY = "UPDATE user_details SET name=?, surname=?, address=?, dataBirth=?, phone_number=? WHERE user_user_id=?";
    //public static final String DELETE_USER_DETAILS_QUERY = "DELETE FROM user_details WHERE user_user_id=?";

    @Override
    public User addUser(User user) throws DAONotFoundException, DAOUserExistException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection = DBConnect.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(ADD_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, Role.USER.toString());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                preparedStatement = connection.prepareStatement(ADD_USER_DETAILS_ID_QUERY);
                preparedStatement.setLong(1, user.getId());
                preparedStatement.executeUpdate();
            }

            connection.commit();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new DAOUserExistException();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new DAONotFoundException("User not added", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public List<User> getUsers() throws DAONotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<User> users;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement(GET_USERS_QUERY);
            resultSet = preparedStatement.executeQuery();

            users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(UserConstant.USER_ID));
                user.setLogin(resultSet.getString(UserConstant.LOGIN));
                user.setPassword(resultSet.getString(UserConstant.PASSWORD));
                user.setEmail(resultSet.getString(UserConstant.EMAIL));
                user.setRole(resultSet.getString(UserConstant.ROLE));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAONotFoundException("something wrong");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public User getUserById(long id) throws DAONotFoundException, UserNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        User user;
        try {
            connection = DBConnect.getConnection();

            preparedStatement = connection.prepareStatement(GET_USER_BY_ID_QUERY);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            user = new User();

            if (!resultSet.next()) {
                throw new UserNotFoundException("User with id=" + id + " not found");
            }
            while (resultSet.next()) {
                user.setId(resultSet.getLong(UserConstant.USER_ID));
                user.setLogin(resultSet.getString(UserConstant.LOGIN));
                user.setPassword(resultSet.getString(UserConstant.PASSWORD));
                user.setEmail(resultSet.getString(UserConstant.EMAIL));
                user.setRole(resultSet.getString(UserConstant.ROLE));
            }
        } catch (SQLException e) {
            throw new DAONotFoundException("something wrong");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws DAONotFoundException, UserNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        User user;

        try {
            connection = DBConnect.getConnection();

            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_QUERY);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();

            user = new User();

            if (!resultSet.next()) {
                throw new UserNotFoundException("User with email=" + email + " not found");
            }

            while (resultSet.next()) {
                user.setId(resultSet.getLong(UserConstant.USER_ID));
                user.setLogin(resultSet.getString(UserConstant.LOGIN));
                user.setPassword(resultSet.getString(UserConstant.PASSWORD));
                user.setEmail(resultSet.getString(UserConstant.EMAIL));
                user.setRole(resultSet.getString(UserConstant.ROLE));
            }
        } catch (SQLException e) {
            throw new DAONotFoundException("something wrong");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public User getUserByLogin(String login) throws UserNotFoundException, DAONotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        User user;

        try {
            connection = DBConnect.getConnection();

            preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_QUERY);
            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            user = new User();

            if (!resultSet.next()) {
                throw new UserNotFoundException("User with login=" + login + " not found");
            }
            while (resultSet.next()) {
                user.setId(resultSet.getLong(UserConstant.USER_ID));
                user.setLogin(resultSet.getString(UserConstant.LOGIN));
                user.setPassword(resultSet.getString(UserConstant.PASSWORD));
                user.setEmail(resultSet.getString(UserConstant.EMAIL));
                user.setRole(resultSet.getString(UserConstant.ROLE));
            }

        } catch (SQLException e) {
            throw new DAONotFoundException("something wrong");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public void updateUserDetails(long id, UserDetails userDetails) throws DAONotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER_DETAILS_QUERY);

            preparedStatement.setString(1, userDetails.getName());
            preparedStatement.setString(2, userDetails.getSurname());
            preparedStatement.setString(3, userDetails.getAddress());
            preparedStatement.setString(4, userDetails.getDataBirth());
            preparedStatement.setString(5, userDetails.getPhoneNumber());
            preparedStatement.setLong(6, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAONotFoundException("User not added", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public UserDetails getUserDetails(long userId) throws DAONotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        UserDetails userDetails;

        try {
            connection = DBConnect.getConnection();

            preparedStatement = connection.prepareStatement(GET_USER_DETAILS_QUERY);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            userDetails = new UserDetails();

            while (resultSet.next()) {
                userDetails.setName(resultSet.getString(UserConstant.NAME));
                userDetails.setSurname(resultSet.getString(UserConstant.SURNAME));
                userDetails.setAddress(resultSet.getString(UserConstant.ADDRESS));
                userDetails.setPhoneNumber(resultSet.getString(UserConstant.PHONE_NUMBER));
            }
        } catch (SQLException e) {
            throw new DAONotFoundException("something wrong");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userDetails;
    }


}
