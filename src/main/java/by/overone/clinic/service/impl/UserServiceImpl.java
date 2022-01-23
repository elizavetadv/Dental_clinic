package by.overone.clinic.service.impl;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.exception.DAOUserNotFoundException;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserUpdatedDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.service.UserService;
import by.overone.clinic.util.validation.exception.ValidationException;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addUser(UserRegistrationDTO userRegistrationDTO) throws ValidationException {
        User user = new User();
        user.setLogin(userRegistrationDTO.getLogin());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(DigestUtils.md5Hex(userRegistrationDTO.getPassword()));

//        UserValidator.validateRegistrationData(userRegistrationDTO);

        userDAO.addUser(user);
    }

    @Override
    public void deleteUserById(long id) {
        getUserById(id);
        userDAO.deleteUserById(id);
    }

    @Override
    public void updateUser(UserUpdatedDTO userUpdatedDTO) {
        getUserById(userUpdatedDTO.getUser_id());

        //validation

        userDAO.updateUser(userUpdatedDTO);
    }

    @Override
    public UserDataDTO getUserById(long id) {
        User user = userDAO.getUserById(id).orElseThrow(() -> new DAOUserNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        return modelMapper.map(user, UserDataDTO.class);
    }

    @Override
    public List<UserDataDTO> getAllUsers() {
        return userDAO.getAllUsers()
                .stream()
                .map(user -> new UserDataDTO(user.getUser_id(), user.getLogin(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDataDTO> getAllUsersByStatus(String status) {
        List<UserDataDTO> userDataDTOs;

        List<User> users = userDAO.getAllUsersByStatus(status);
        userDataDTOs = users.stream()
                .map(user -> new UserDataDTO(user.getUser_id(), user.getLogin(), user.getEmail()))
                .collect(Collectors.toList());

        return userDataDTOs;
    }

    @Override
    public List<UserDataDTO> getAllUsersByRole(String role) {
        List<User> users = userDAO.getAllUsersByRole(role);

        return users.stream()
                .map(user -> new UserDataDTO(user.getUser_id(), user.getLogin(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDataDTO getUserBySurname(String surname) {
        User user = userDAO.getUserBySurname(surname).orElseThrow(() -> new DAOUserNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        return modelMapper.map(user, UserDataDTO.class);
    }
}
