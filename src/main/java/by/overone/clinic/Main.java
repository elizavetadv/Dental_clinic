package by.overone.clinic;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
        List<User> users = null;

        try {
            users = userDAO.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        users.stream().forEach(System.out::println);
    }

}
