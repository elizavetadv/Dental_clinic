package by.overone.clinic.dao;

import by.overone.clinic.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
    User getUserById(long id);
    User getUserByEmail(String email);

    User addUser(User user);

    User updateUser(User user);

    User deleteUser(long id);
}
