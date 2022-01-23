package by.overone.clinic.util.validation;

public class DoctorDetailsValidator {
    public final static String NAME_REGEX = "^[a-zA-Z]+$";
    public final static String SURNAME_REGEX = "^[a-zA-Z]+$";

//    public static void validateDoctorDetails(DoctorDetails doctorDetails) throws ValidationException {
//        if (!validateName(doctorDetails.getName())) {
//            throw new ValidationException("incorrect name");
//        }
//        if (!validateSurname(doctorDetails.getSurname())) {
//            throw new ValidationException("incorrect surname");
//        }
//        if (!validateDoctorType(doctorDetails.getDoctorType())) {
//            throw new ValidationException("incorrect doctorType");
//        }
//    }
//
//    private static boolean validateName(String name) {
//        return name != null && !name.isBlank() && name.matches(NAME_REGEX);
//    }
//
//    private static boolean validateSurname(String surname) {
//        return surname != null && !surname.isBlank() && surname.matches(SURNAME_REGEX);
//    }

//   public static boolean validateDoctorType(String doctorType){
//        return doctorType.equals(DoctorType.ORTHODONTIST.toString().toLowerCase())
//                | doctorType.equals(DoctorType.ORTHOPEDIST.toString().toLowerCase())
//                | doctorType.equals(DoctorType.SURGEON.toString().toLowerCase());
//    }
}
