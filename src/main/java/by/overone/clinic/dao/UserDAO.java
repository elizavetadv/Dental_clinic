package by.overone.clinic.dao;

import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserUpdatedDTO;
import by.overone.clinic.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface with methods for the user
 * @see by.overone.clinic.dao.impl.UserDAOImpl with realization of all methods
 */
public interface UserDAO {

    void addUser(UserRegistrationDTO userRegistrationDTO);

    void deleteUserById(long id);

    void updateUser(long id, UserUpdatedDTO userUpdatedDTO);

    Optional<User> getUserById(long id);

    List<User> getAllUsers();

    List<User> getUser(String status, String role, String surname);
}
