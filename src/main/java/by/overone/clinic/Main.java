package by.overone.clinic;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.model.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDAO userDAO = new UserDAOImpl();

        userDAO.getUsers().stream().forEach(System.out::println);

//        User userNew = new User(0,"Nikita", "64235", "nikita@gmail.com");
//        userDAO.addUser(userNew);
//        userDAO.getUsers().stream().forEach(System.out::println);

        System.out.println("\n" + userDAO.getUserByEmail("misha@gmail.com"));
        System.out.println("\n" + userDAO.getUserById(1));

        System.out.println("\n" + userDAO.deleteUser(5) + "\n");
        userDAO.getUsers().stream().forEach(System.out::println);

    }

}
