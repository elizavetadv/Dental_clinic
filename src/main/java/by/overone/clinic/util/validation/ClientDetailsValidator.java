package by.overone.clinic.util.validation;

public class ClientDetailsValidator {
    public final static String NAME_REGEX = "^[a-zA-Z]+$";
    public final static String SURNAME_REGEX = "^[a-zA-Z]+$";
    public final static String ADDRESS_REGEX = "^[a-zA-Z]+$";
    public final static String DATA_BIRTH_REGEX = "^(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)$";
    public final static String PHONE_NUMBER_REGEX = "\\+375-[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}";
}
