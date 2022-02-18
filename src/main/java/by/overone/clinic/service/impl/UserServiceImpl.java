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
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * This method calls the method from dao to add user to database
     *
     * @param userRegistrationDTO data for registration
     */
    @Override
    public void addUser(UserRegistrationDTO userRegistrationDTO) {
        userDAO.addUser(userRegistrationDTO);
    }

    /**
     * This method calls the method to delete user from UserDAO
     * It throws DAONotExistException if user is not found in method getUserById or if we try to delete a deleted user
     *
     * @param id user id
     */
    @Override
    public void deleteUserById(long id) {
        getUserById(id);
        userDAO.deleteUserById(id);
    }

    /**
     * This method calls the method from dao to update user data
     *
     * @param id user id
     * @param userUpdatedDTO user data available to updating
     */
    @Override
    public void updateUser(long id, UserUpdatedDTO userUpdatedDTO) {
        getUserById(id);
        userDAO.updateUser(id, userUpdatedDTO);
    }

    /**
     * This method calls the method from dao to get user from db.
     * It throws DAONotExistException when user is not found
     *
     * @param id user id
     * @return user data available for him
     */
    @Override
    public UserDataDTO getUserById(long id) {
        User user = userDAO.getUserById(id).orElseThrow(() -> new DAONotExistException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));

        UserDataDTO userDataDTO = new UserDataDTO();
        BeanUtils.copyProperties(user, userDataDTO);
        return userDataDTO;
    }

    /**
     * This method calls the method from dao to get all users from database
     *
     * @return list of user dto(login and email)
     */
    @Override
    public List<UserDataDTO> getAllUsers() {
        return userDAO.getAllUsers()
                .stream()
                .map(user -> new UserDataDTO(user.getLogin(), user.getEmail()))
                .collect(Collectors.toList());
    }

    /**
     * This method calls the method that is used to get users by status, role, surname or a combination of them.
     * If incorrect status or role is passed to the method, DAOIncorrectDataException is thrown
     *
     * @param status  status
     * @param role role
     * @param surname surname
     * @return list of user dto(login and email)
     */
    @Override
    public List<UserDataDTO> getUser(String status, String role, String surname) {
        if ((!status.equals("") && !status.equals(Status.ACTIVE.toString()) && !status.equals(Status.DELETED.toString())) ||
                (!role.equals("") && !role.equals(Role.CLIENT.toString()) && !role.equals(Role.USER.toString()) && !role.equals(Role.DOCTOR.toString()))) {
            throw new DAOIncorrectDataException(ExceptionCode.INCORRECT_QUERY_DATA.getErrorCode());
        }
        return userDAO.getUser(status, role, surname).stream()
                .map(user -> new UserDataDTO(user.getLogin(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
