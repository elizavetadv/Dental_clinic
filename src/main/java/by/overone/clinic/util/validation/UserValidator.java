package by.overone.clinic.util.validation;

import by.overone.clinic.dto.UserRegistrationDTO;

public class UserValidator {
    private final static String LOGIN_REGEX = "^[\\w]{5,10}$";
    private final static String EMAIL_REGEX = "^[^\\s]+@[//w]+\\.[a-z]+$";

    public static boolean validateRegistrationData(UserRegistrationDTO userRegistrationDTO){
        return validateLogin(userRegistrationDTO.getLogin()) && validateEmail(userRegistrationDTO.getEmail()) && userRegistrationDTO.getPassword().length() > 5;
        //CommonValidator.validateEmail(userRegistrationDTO.getEmail()); //&& остальные поля валидаторы
    }

    private static boolean validateLogin(String login){
        return login != null && !login.isBlank() && login.matches(LOGIN_REGEX);
    }

    private static boolean validateEmail(String email){
        return  email != null && !email.isBlank() && email.matches(EMAIL_REGEX);
    }

    //для пароля сделать

    //кидать в методах исключения с сообщениями об ошибках
}
