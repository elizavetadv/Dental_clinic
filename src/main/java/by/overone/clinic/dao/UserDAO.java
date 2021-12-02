package by.overone.clinic.dao;

import by.overone.clinic.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getUsers() throws SQLException;
    User getUserById(long id);
    User getUserByEmail(String email);

    User addUser(User user);

    User updateUser(User user);

    User deleteUser(long id);
}
