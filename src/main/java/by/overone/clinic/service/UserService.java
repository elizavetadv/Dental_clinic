package by.overone.clinic.service;

import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.util.validation.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(UserRegistrationDTO userRegistrationDTO) throws ValidationException;

    void deleteUserById(long id);

    UserDataDTO getUserById(long id);

    List<UserDataDTO> getAllUsers();

    List<UserDataDTO> getAllUsersByStatus(String status);

    List<UserDataDTO> getAllUsersByRole(String role);

    Optional<User> getUserBySurname(String surname);

}
