package by.overone.clinic.util.validation;

import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.util.validation.exception.ValidationException;

public class ClientDetailsValidator {
    private final static String NAME_REGEX = "^[a-zA-Z]+$";
    private final static String SURNAME_REGEX = "^[a-zA-Z]+$";
    private final static String ADDRESS_REGEX = "^[a-zA-Z]+$";
    private final static String DATA_BIRTH_REGEX = "^(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d)$";
    private final static String PHONE_NUMBER_REGEX = "\\+375-[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}";

    public static void validateClientDetails(ClientDetails userDetails) throws ValidationException {
        if (!validateName(userDetails.getName())) {
            throw new ValidationException("incorrect name");
        }
        if (!validateSurname(userDetails.getSurname())) {
            throw new ValidationException("incorrect surname");
        }
        if (!validateAddress(userDetails.getAddress())) {
            throw new ValidationException("incorrect address");
        }
        if (!validateDataBirth(userDetails.getDataBirth())) {
            throw new ValidationException("incorrect birthday data");
        }
        if (!validatePhoneNumber(userDetails.getPhoneNumber())) {
            throw new ValidationException("incorrect phone number");
        }
    }

    private static boolean validateName(String name) {
        return name != null && !name.isBlank() && name.matches(NAME_REGEX);
    }

    private static boolean validateSurname(String surname) {
        return surname != null && !surname.isBlank() && surname.matches(SURNAME_REGEX);
    }

    private static boolean validateAddress(String address) {
        return address != null && !address.isBlank() && address.matches(ADDRESS_REGEX);
    }

    private static boolean validateDataBirth(String dataBirth) {
        return dataBirth != null && !dataBirth.isBlank() && dataBirth.matches(DATA_BIRTH_REGEX);
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && !phoneNumber.isBlank() && phoneNumber.matches(PHONE_NUMBER_REGEX);
    }
}
