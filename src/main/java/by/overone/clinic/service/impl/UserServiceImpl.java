package by.overone.clinic.service.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.exception.DAONotFoundException;
import by.overone.clinic.dao.exception.DAOUserExistException;
import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetails;
import by.overone.clinic.service.UserService;
import by.overone.clinic.service.exception.ServiceException;
import by.overone.clinic.service.exception.ServiceUserExistException;
import by.overone.clinic.util.validation.UserValidator;
import by.overone.clinic.util.validation.exception.ValidationException;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<UserDataDTO> getAllUsers() throws ServiceException {
        List<UserDataDTO> userDataDTOs;

        try {
            List<User> users = userDAO.getUsers();
            userDataDTOs = users.stream()
                    .map(user -> new UserDataDTO(user.getId(), user.getLogin(), user.getEmail()))
                    .collect(Collectors.toList());
        } catch (DAONotFoundException e) {
            throw new ServiceException(e);
        }
        return userDataDTOs;
    }

    @Override
    public UserDataDTO addUser(UserRegistrationDTO userRegistrationDTO) throws ServiceException, ServiceUserExistException, ValidationException {
        User user = new User();
        user.setLogin(userRegistrationDTO.getLogin());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(DigestUtils.md5Hex(userRegistrationDTO.getPassword()));

        if (!UserValidator.validateRegistrationData(userRegistrationDTO)){
            throw new ValidationException("Incorrect registration data");
        }

        try {
            user = userDAO.addUser(user);
        } catch (DAOUserExistException e) {
            throw new ServiceUserExistException("User already exist");
        }catch (DAONotFoundException e) {
            throw new ServiceException("UserService. addUser, User not added", e);
        }
        return new UserDataDTO(0, user.getLogin(), user.getEmail());
    }

    public UserDetails getUserDetails(long userId) throws ServiceException {
        UserDetails userDetails = null;
        try {
            userDetails = userDAO.getUserDetails(userId);
        } catch (DAONotFoundException e) {
            throw new ServiceException("Exception. UserServiceImpl. getUserDetail", e);
        }
        return userDetails;
    }
}
