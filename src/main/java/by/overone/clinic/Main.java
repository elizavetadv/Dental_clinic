package by.overone.clinic;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.exception.DAOException;
import by.overone.clinic.dao.exception.DAOUserNotFoundException;
import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.DoctorDetails;
import by.overone.clinic.model.User;
import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.service.UserService;
import by.overone.clinic.service.exception.ServiceException;
import by.overone.clinic.service.exception.ServiceUserNotFoundException;
import by.overone.clinic.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

public class Main {
    public static void main(String[] args) throws ServiceException {



        UserService userService = new UserServiceImpl();
        System.out.println("hello");
//        try {
//            List<UserDataDTO> users = userService.getAllUsers();
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }


        UserRegistrationDTO userRegistrationDTO2 = new UserRegistrationDTO();

        userRegistrationDTO2.setLogin("olya54");
        userRegistrationDTO2.setPassword("1234#oL");
        userRegistrationDTO2.setEmail("olya@gmail.com");

        ClientDetails userDetails2 = new ClientDetails();
        userDetails2.setName("Olya");
        userDetails2.setSurname("Savichenko");
        userDetails2.setAddress("Minsk");
        userDetails2.setDataBirth("25.01.1999");
        userDetails2.setPhoneNumber("+375-75-004-70-86");

        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setLogin("kirill89");
        userRegistrationDTO.setPassword("Kirill5$2");
        userRegistrationDTO.setEmail("kirill@gmail.com");

        UserRegistrationDTO userRegistrationDTO1 = new UserRegistrationDTO();
        userRegistrationDTO1.setLogin("artyom54");
        userRegistrationDTO1.setPassword("Artyom5$2");
        userRegistrationDTO1.setEmail("artyom@gmail.com");

        User user1 = new User();
        user1.setLogin("artyom54");
        user1.setPassword("Artyom5$2");
        user1.setEmail("artyom@gmail.com");

        ClientDetails userDetails = new ClientDetails();
        userDetails.setName("Kirill");
        userDetails.setSurname("Holland");
        userDetails.setAddress("Gomel");
        userDetails.setDataBirth("11.01.2001");
        userDetails.setPhoneNumber("+375-44-416-58-26");

        ClientDetails userDetails1 = new ClientDetails();
        userDetails1.setName("Artyom");
        userDetails1.setSurname("Jobs");
        userDetails1.setAddress("Minsk");
        userDetails1.setDataBirth("02.10.1998");
        userDetails1.setPhoneNumber("+375-65-124-75-86");

        DoctorDetails doctorDetails = new DoctorDetails();
        doctorDetails.setName("Artyom");
        doctorDetails.setSurname("Gates");
        doctorDetails.setDoctorType("orthodontist");


//        System.out.println(userService.getUserDetails(1));


//        try {
//            userService.addUser(userRegistrationDTO2);
//        } catch (DAONotFoundException e) {
//            e.printStackTrace();
//        } catch (ValidationException e) {
//            e.printStackTrace();
//        }catch (ServiceUserExistException e) {
//            e.printStackTrace();
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//        UserDAO userDAO = new UserDAOImpl();

//        try {
//            System.out.println(userDAO.getClientDetails(3));
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }

//        try {
//            userService.updateDoctorDetails( 2, doctorDetails);
//        } catch (ValidationException e) {
//            e.printStackTrace();
//        }catch (ServiceException e) {
//            e.printStackTrace();
//        }

//        try {
//            userService.getAllUsersByRole("USER").stream().forEach(System.out::println);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//        try {
//            userService.addDoctorDetails(userDAO.getUserById(2), doctorDetails);
////            userService.addClientDetails(userDAO.getUserById(3), userDetails2);
//        } catch (ValidationException e) {
//            e.printStackTrace();
//        } catch (DAONotFoundException e) {
//            e.printStackTrace();
//        } catch (UserNotFoundException e) {
//            e.printStackTrace();
//        } catch (ServiceUserDetailsExistException e) {
//            e.printStackTrace();
//        }
//        try {
//            System.out.println(userService.getUserById(1));
//        } catch (ServiceUserNotFoundException e) {
//            e.printStackTrace();
//        }catch (ServiceException e) {
//            e.printStackTrace();
//        }
//        try {
//            userService.deleteUserById(2);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }

//        try {
//            userService.getAllUsersByStatus("DELETED").stream().forEach(System.out::println);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//        try {
//            userService.addUserDetails(userDAO.getUserById(2), userDetails1);
//        } catch (ValidationException e) {
//            e.printStackTrace();
//        } catch (DAONotFoundException e) {
//            e.printStackTrace();
//        } catch (UserNotFoundException e) {
//            e.printStackTrace();
//        }

//        try {
//            userService.addUser(userRegistrationDTO2);
//        } catch (ValidationException e) {
//            e.printStackTrace();
//        }catch (DAONotFoundException e) {
//            e.printStackTrace();
//        }  catch (ServiceUserExistException e) {
//            e.printStackTrace();
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }

//        UserDAO userDAO = new UserDAOImpl();
//
//        try {
//            userService.addUserDetails(userDAO.getUserById(27), userDetails);
//        }catch (ValidationException e) {
//            e.printStackTrace();
//        } catch (UserNotFoundException e) {
//            e.printStackTrace();
//        }catch (DAONotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            System.out.println(userDAO.getUserDetails(7));
//        } catch (DAONotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            userDAO.addUser(user);
//        } catch (DAONotFoundException | DAOUserExistException e) {
//            e.printStackTrace();
//        }


//        try {
//            userService.addUser(userRegistrationDTO);
//        } catch (DAONotFoundException e) {
//            e.printStackTrace();
//        } catch (ValidationException e) {
//            e.printStackTrace();
//        } catch (ServiceUserExistException e) {
//            e.printStackTrace();
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }


    }

}
