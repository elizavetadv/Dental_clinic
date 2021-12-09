package by.overone.clinic.util.validation;

import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.util.validation.exception.ValidationException;

public class UserValidator {
    private final static String LOGIN_REGEX = "^[\\w]{5,10}$";
    private final static String EMAIL_REGEX = "^[^\\s]+@[\\w]+\\.[a-z]+$";
    private final static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";

    public static boolean validateRegistrationData(UserRegistrationDTO userRegistrationDTO) throws ValidationException {
        if (!validateLogin(userRegistrationDTO.getLogin()) || !validatePassword(userRegistrationDTO.getPassword())) {
            throw new ValidationException("incorrect login or password");
        }
        if (!validateEmail(userRegistrationDTO.getEmail())) {
            throw new ValidationException("incorrect email");
        }
        return validateLogin(userRegistrationDTO.getLogin()) && validateEmail(userRegistrationDTO.getEmail())
                && validatePassword(userRegistrationDTO.getPassword());
    }

    private static boolean validateLogin(String login) {
        return login != null && !login.isBlank() && login.matches(LOGIN_REGEX);
    }

    private static boolean validateEmail(String email) {
        return email != null && !email.isBlank() && email.matches(EMAIL_REGEX);
    }

    private static boolean validatePassword(String password) {
        return password != null && !password.isBlank() && password.matches(PASSWORD_REGEX);
    }
}
