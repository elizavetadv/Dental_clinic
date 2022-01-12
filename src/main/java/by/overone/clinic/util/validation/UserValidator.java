package by.overone.clinic.util.validation;

import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.util.validation.exception.*;

public class UserValidator {
    private final static String LOGIN_REGEX = "^[\\w]{5,10}$";
    private final static String EMAIL_REGEX = "^[^\\s]+@[\\w]+\\.[a-z]+$";
    private final static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";

    public static void validateRegistrationData(UserRegistrationDTO userRegistrationDTO) throws ValidationException {
        validateLogin(userRegistrationDTO.getLogin());
//        try {
//            if (!validateLogin(userRegistrationDTO.getLogin()) || !validatePassword(userRegistrationDTO.getPassword())) {
//                throw new ValidationException("incorrect login or password");
//            }
//        } catch (EmptyFieldException e) {
//            e.printStackTrace();
//        } catch (FieldIsBlankException e) {
//            e.printStackTrace();
//        } catch (IncorrectFieldsLengthException e) {
//            e.printStackTrace();
//        }
//        if (!validateEmail(userRegistrationDTO.getEmail())) {
//            throw new ValidationException("incorrect email");
//        }
    }

    private static boolean validateLogin(String login) throws ValidationException {
        if (login == null){
            throw new EmptyFieldException("Field with login is empty");
        } else if (login.isBlank()){
            throw new FieldIsBlankException("Field with login consists of blanks");
        } else if (login.length() < 5){
            throw new IncorrectFieldsLengthException("Login is less than 5 characters ");
        }else if(login.length() > 10){
            throw new IncorrectFieldsLengthException("Login is more than 10 characters ");
        }else if(!login.matches(LOGIN_REGEX)){
            throw new ValidationException("Incorrect login");
        }
        return true;
    }

    private static boolean validateEmail(String email) throws ValidationException {
        if(email == null){
            throw new EmptyFieldException("Field with email is empty");
        } else if (email.isBlank()){
            throw new FieldIsBlankException("Field with email consists of blanks");
        } else if (email.matches(EMAIL_REGEX)){
            throw new ValidationException("Incorrect email");
        }
        return true;
    }

    private static boolean validatePassword(String password) {
        return password != null && !password.isBlank() && password.matches(PASSWORD_REGEX);
    }
}
