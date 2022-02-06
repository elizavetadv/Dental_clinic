package by.overone.clinic.util.validation;

public class UserValidator {
    public final static String LOGIN_REGEX = "^[a-z0-9]{5,10}$";
    public final static String EMAIL_REGEX = "^[^\\s]+@[\\w]+\\.[a-z]+$";
    public final static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";

    public static void validateLoggingData(){

    }
}
