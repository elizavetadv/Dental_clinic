package by.overone.clinic.controller.exception;

import by.overone.clinic.dao.exception.DAODetailsExistException;
import by.overone.clinic.dao.exception.DAODetailsNotFoundException;
import by.overone.clinic.dao.exception.DAOUserExistException;
import by.overone.clinic.dao.exception.DAOUserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(DAOUserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundHandler(DAOUserNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setException(e.getClass().getName());
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

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //isn't used because of SQLIntegrityConstraintViolationExceptionHandler
    @ExceptionHandler(DAOUserExistException.class)
    public ResponseEntity<ExceptionResponse> userExistHandler(DAOUserExistException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setException(e.getClass().getName());
        response.setErrorCode("3");
        response.setMessage("User already exists");

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    //isn't used
    @ExceptionHandler(DAODetailsExistException.class)
    public ResponseEntity<ExceptionResponse> detailsExistHandler(DAODetailsExistException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getName());
        response.setErrorCode(e.getMessage());
        String message = "";
        switch (e.getMessage()) {
            case "4":
                message = "Clients Details already exists";
                break;
            case "5":
                message = "Doctor Details already exists";
                break;
        }
        response.setMessage(message);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @ExceptionHandler(DAODetailsNotFoundException.class)
    public ResponseEntity<ExceptionResponse> detailsNotExistHandler(DAODetailsNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getName());
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
    public ResponseEntity<ExceptionResponse> SQLIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException e){
        ExceptionResponse response = new ExceptionResponse();

        response.setException(e.getClass().getSimpleName());
        response.setErrorCode("6");
        response.setMessage("Such object exists");

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

}
