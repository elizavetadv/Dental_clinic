package by.overone.clinic.controller.exception;

public enum ExceptionCode {
    NOT_EXISTING_USER ("2"),
    EXISTING_CLIENT_DETAILS("4"),
    EXISTING_DOCTOR_DETAILS("5"),
    NOT_EXISTING_CLIENT_DETAILS("7"),
    NOT_EXISTING_DOCTOR_DETAILS("8"),
    EXISTING_USER ("3");

    private final String errorCode;

    ExceptionCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
