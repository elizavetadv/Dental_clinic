package by.overone.clinic.dao;

import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserUpdatedDTO;
import by.overone.clinic.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    void addUser(UserRegistrationDTO userRegistrationDTO);

    void deleteUserById(long id);

    void updateUser(long id, UserUpdatedDTO userUpdatedDTO);

    Optional<User> getUserById(long id);

    List<User> getAllUsers();

    List<User> getAllUsersByStatus(String status);

    List<User> getAllUsersByRole(String role);

    List<User> getUserBySurname(String surname);
}
