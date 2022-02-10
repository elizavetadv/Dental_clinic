package by.overone.clinic.util.validation;

import by.overone.clinic.dto.UserLoggingDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import org.apache.commons.codec.digest.DigestUtils;

public class UserValidator {
    public final static String LOGIN_REGEX = "^[a-z0-9]{5,10}$";
    public final static String EMAIL_REGEX = "^[^\\s]+@[\\w]+\\.[a-z]+$";
    public final static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";

    public static boolean validateLoggingData(UserLoggingDTO userLoggingDTO, UserRegistrationDTO userFromDB){
        return userLoggingDTO.getLogin().equals(userFromDB.getLogin())
                && DigestUtils.md5Hex(userLoggingDTO.getPassword()).equals(userFromDB.getPassword());
    }
}
