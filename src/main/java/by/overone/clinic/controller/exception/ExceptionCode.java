package by.overone.clinic.controller.exception;

public enum ExceptionCode {
    NOT_EXISTING_USER ("2"),
    EXISTING_CLIENT_DETAILS("4"),
    EXISTING_DOCTOR_DETAILS("5"),
    NOT_EXISTING_CLIENT_DETAILS("7"),
    NOT_EXISTING_DOCTOR_DETAILS("8"),
    EXISTING_USER ("3"),
    NOT_EXISTING_RECORD("10"),
    NOT_UPDATING_RECORD("11"),
    NOT_DELETING_RECORD("12"),
    NOT_DELETING_RECORD_1("13"),
    INCORRECT_DAY("14"),
    INCORRECT_MONTH("15"),
    INCORRECT_YEAR("16"),
    NOT_EXISTING_RECEPTION("17"),
    NOT_EXISTING_TIME("18"),
    INCORRECT_QUERY_DATA("19"),
    IMPOSSIBLE_ACTION("21");

    private final String errorCode;

    ExceptionCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
