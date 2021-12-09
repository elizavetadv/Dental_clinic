package by.overone.clinic;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.service.UserService;
import by.overone.clinic.service.exception.ServiceException;
import by.overone.clinic.service.impl.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) throws ServiceException {
        UserService userService = new UserServiceImpl();

        List<UserDataDTO> users = userService.getAllUsers();

        UserDAO userDAO = new UserDAOImpl();

    }

}
