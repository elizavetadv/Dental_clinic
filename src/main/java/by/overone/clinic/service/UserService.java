package by.overone.clinic.service;

import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserUpdatedDTO;

import java.util.List;

/**
 * @see by.overone.clinic.service.impl.UserServiceImpl with realization of all methods
 */
public interface UserService {
    void addUser(UserRegistrationDTO userRegistrationDTO);

    void deleteUserById(long id);

    void updateUser(long id, UserUpdatedDTO userUpdatedDTO);

    UserDataDTO getUserById(long id);

    List<UserDataDTO> getAllUsers();

    List<UserDataDTO> getUser(String status, String role, String surname);
}
