package by.overone.clinic.controller.exception;

import by.overone.clinic.dao.exception.DAODetailsNotFoundException;
import by.overone.clinic.dao.exception.DAOUserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(DAOUserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundHandler(DAOUserNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        response.setMessage("User not found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponse> sqlExceptionHandler(SQLException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setException(e.getClass().getSimpleName());
        response.setErrorCode("1");
        response.setMessage("Something is wrong with BD");

        log.error("error", e);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DAODetailsNotFoundException.class)
    public ResponseEntity<ExceptionResponse> detailsNotExistHandler(DAODetailsNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        String message = "";
        switch (e.getMessage()) {
            case "7":
                message = "Clients Details don't exist";
                break;
            case "8":
                message = "Doctor Details don't exist";
                break;
        }
        response.setMessage(message);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> SQLIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException e) {
        ExceptionResponse response = new ExceptionResponse();

        String message = e.getMessage();

        if (message.contains("user")) {
            response.setException("DAOUserExistException");
            response.setErrorCode(ExceptionCode.EXISTING_USER.getErrorCode());
            response.setMessage("User already exists");
        } else if (message.contains("doctor_details")) {
            response.setException("DAODetailsExistException");
            response.setErrorCode(ExceptionCode.EXISTING_DOCTOR_DETAILS.getErrorCode());
            response.setMessage("Doctor Details already exists");
        } else if (message.contains("client_details")) {
            response.setException("DAODetailsExistException");
            response.setErrorCode(ExceptionCode.EXISTING_CLIENT_DETAILS.getErrorCode());
            response.setMessage("Client Details already exists");
        } else {
            response.setException(e.getClass().getSimpleName());
            response.setErrorCode("6");
            response.setMessage("Such object exists");
        }
        log.error("error", e);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setException(e.getClass().getSimpleName());
        response.setErrorCode("9");

        String message = e.getMessage();
        String field = "";

        if(message.contains("login")){
            field = " in field with login";
        } else if (message.contains("password")) {
            field = " in field with password";
        } else if(message.contains("email")){
            field = " in field with email";
        } else if(message.contains("'name'")){
            field = " in field with name";
        } else if(message.contains("surname")){
            field = " in field with surname";
        } else if(message.contains("address")){
            field = " in field with address";
        } else if(message.contains("dataBirth")){
            field = " in field with data birthday";
        } else if(message.contains("phoneNumber")){
            field = " in field with phone number";
        } else if(message.contains("doctorType")){
            field = " in field with doctor type";
        }

        response.setMessage("Incorrect data" + field);

        log.error("error", e);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
