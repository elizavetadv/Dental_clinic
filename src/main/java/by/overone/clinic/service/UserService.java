package by.overone.clinic.service;

import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserUpdatedDTO;

import java.util.List;

public interface UserService {
    void addUser(UserRegistrationDTO userRegistrationDTO);

    void deleteUserById(long id);

    void updateUser(long id, UserUpdatedDTO userUpdatedDTO);

    UserDataDTO getUserById(long id);

    List<UserDataDTO> getAllUsers();

    List<UserDataDTO> getAllUsersByStatus(String status);

    List<UserDataDTO> getAllUsersByRole(String role);

    List<UserDataDTO> getUserBySurname(String surname);

}
