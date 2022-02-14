package by.overone.clinic.service.impl;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.exception.DAOIncorrectDataException;
import by.overone.clinic.dao.exception.DAONotExistException;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserUpdatedDTO;
import by.overone.clinic.model.Role;
import by.overone.clinic.model.Status;
import by.overone.clinic.model.User;
import by.overone.clinic.service.UserService;
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
    public void addUser(UserRegistrationDTO userRegistrationDTO){
        userDAO.addUser(userRegistrationDTO);
    }

    @Override
    public void deleteUserById(long id) {
        getUserById(id);
        userDAO.deleteUserById(id);
    }

    @Override
    public void updateUser(long id, UserUpdatedDTO userUpdatedDTO) {
        getUserById(id);
        userDAO.updateUser(id, userUpdatedDTO);
    }

    @Override
    public UserDataDTO getUserById(long id) {
        User user = userDAO.getUserById(id).orElseThrow(() -> new DAONotExistException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        return modelMapper.map(user, UserDataDTO.class);
    }

    @Override
    public List<UserDataDTO> getAllUsers() {
        return userDAO.getAllUsers()
                .stream()
                .map(user -> new UserDataDTO(user.getLogin(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDataDTO> get(String status, String role, String surname) {
        if(( !status.equals("") && !status.equals(Status.ACTIVE.toString()) && !status.equals(Status.DELETED.toString())) ||
                (!role.equals("") && !role.equals(Role.CLIENT.toString()) && !role.equals(Role.USER.toString()) && !role.equals(Role.DOCTOR.toString()))){
            throw new DAOIncorrectDataException(ExceptionCode.INCORRECT_QUERY_DATA.getErrorCode());
        }
        return userDAO.get(status, role, surname).stream()
                .map(user -> new UserDataDTO(user.getLogin(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
