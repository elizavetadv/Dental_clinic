package by.overone.clinic.service;

import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserUpdatedDTO;
import by.overone.clinic.util.validation.exception.ValidationException;

import java.util.List;

public interface UserService {

    void addUser(UserRegistrationDTO userRegistrationDTO) throws ValidationException;

    void deleteUserById(long id);

    void updateUser(UserUpdatedDTO userUpdatedDTO);

    UserDataDTO getUserById(long id);

    List<UserDataDTO> getAllUsers();

    List<UserDataDTO> getAllUsersByStatus(String status);

    List<UserDataDTO> getAllUsersByRole(String role);

    UserDataDTO getUserBySurname(String surname);

}
