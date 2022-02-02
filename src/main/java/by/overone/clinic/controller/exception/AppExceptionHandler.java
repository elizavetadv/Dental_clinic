package by.overone.clinic.controller.exception;

import by.overone.clinic.dao.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

//    @ExceptionHandler(DAOUserNotFoundException.class)
//    public ResponseEntity<ExceptionResponse> userNotFoundHandler(DAOUserNotFoundException e, WebRequest webRequest) {
//        ExceptionResponse response = new ExceptionResponse();
//
//        response.setException(e.getClass().getSimpleName());
//        response.setErrorCode(e.getMessage());
//        response.setMessage(messageSource.getMessage(e.getMessage(), new Object[]{"aa", "bb"}, webRequest.getLocale()));
//
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(DAONotExistException.class)
    public ResponseEntity<ExceptionResponse> notExistHandler(DAONotExistException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        String message = "";
        switch (e.getMessage()) {
            case "2":
                message = "User not found";
                break;
            case "7":
                message = "Client Details not found";
                break;
            case "8":
                message = "Doctor Details not found";
                break;
            case "10":
                message = "Record not found";
                break;
            case "17":
                message = "Reception not found";
                break;
            case "18":
                message = "Record not made. Time is busy. Please choose another";
                break;
        }
        response.setMessage(message);

        log.error("error", e);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ExceptionResponse> list = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ExceptionResponse("MethodArgumentNotValidException", error.getField() + " is incorrect...", "9"))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(ex.getClass().getSimpleName());
        response.setErrorCode("405");
        response.setMessage("Not allowed");
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(DAORecordException.class)
    public ResponseEntity<ExceptionResponse> recordHandler(DAORecordException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        String message = "";
        switch (e.getMessage()) {
            case "11":
                message = "Can't update record. You may only delete it and make record again";
                break;
            case "12":
                message = "Can't delete record which is done";
                break;
            case "13":
                message = "Can't delete record which is confirmed. We can do it only after your call";
                break;
        }
        response.setMessage(message);

        log.error("error", e);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DAOIncorrectDataException.class)
    public ResponseEntity<ExceptionResponse> incorrectDataDoctorTimetableHandler(DAOIncorrectDataException e) {
        ExceptionResponse response = new ExceptionResponse();

        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        String message = "";
        switch (e.getMessage()) {
            case "14":
                message = "Incorrect day";
                break;
            case "15":
                message = "Incorrect month";
                break;
            case "16":
                message = "Incorrect year";
                break;
            case "19":
                message = "Incorrect query data";
                break;
        }
        response.setMessage(message);

        log.error("error", e);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
