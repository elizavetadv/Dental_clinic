package by.overone.clinic.dao;

import by.overone.clinic.dao.exception.DAONotFoundException;
import by.overone.clinic.dao.exception.DAOUserExistException;
import by.overone.clinic.dao.exception.UserNotFoundException;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;

import java.util.List;

public interface UserDAO {
    List<User> getUsers() throws DAONotFoundException;

    User getUserById(long id) throws DAONotFoundException, UserNotFoundException;

    User getUserByEmail(String email) throws DAONotFoundException, UserNotFoundException;

    User getUserByLogin(String login) throws UserNotFoundException, DAONotFoundException;

    User addUser(User user) throws DAONotFoundException, DAOUserExistException;

    //User updateUser(User user) throws SQLException;

    //void deleteUser(long id) throws SQLException, UserNotFoundException, DAONotFoundException;

    UserDetails getUserDetails(long id) throws DAONotFoundException;
}
