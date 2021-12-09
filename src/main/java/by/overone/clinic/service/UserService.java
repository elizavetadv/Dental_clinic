package by.overone.clinic.service;

import by.overone.clinic.dao.exception.DAONotFoundException;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.UserDetails;
import by.overone.clinic.service.exception.ServiceException;
import by.overone.clinic.service.exception.ServiceUserExistException;
import by.overone.clinic.util.validation.exception.ValidationException;

import java.util.List;

public interface UserService {

    List<UserDataDTO> getAllUsers() throws ServiceException;

    UserDataDTO addUser(UserRegistrationDTO userRegistrationDTO) throws DAONotFoundException, ServiceException, ServiceUserExistException, ValidationException;

    UserDetails getUserDetails(long userId) throws ServiceException;
}
