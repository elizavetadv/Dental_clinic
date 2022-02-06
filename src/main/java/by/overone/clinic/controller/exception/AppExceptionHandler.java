package by.overone.clinic.controller.exception;

import by.overone.clinic.dao.exception.DAOIncorrectDataException;
import by.overone.clinic.dao.exception.DAONotExistException;
import by.overone.clinic.dao.exception.DAORecordException;
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

    @ExceptionHandler(DAONotExistException.class)
    public ResponseEntity<ExceptionResponse> notExistHandler(DAONotExistException e, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        response.setMessage(messageSource.getMessage(e.getMessage(), null, webRequest.getLocale()));

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
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<ExceptionResponse> list = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ExceptionResponse("MethodArgumentNotValidException", error.getField()
                        + " is incorrect...", "9"))
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
    public ResponseEntity<ExceptionResponse> recordHandler(DAORecordException e, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();

        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        response.setMessage(messageSource.getMessage(e.getMessage(), null, webRequest.getLocale()));

        log.error("error", e);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DAOIncorrectDataException.class)
    public ResponseEntity<ExceptionResponse> incorrectDataDoctorTimetableHandler(DAOIncorrectDataException e, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();

        response.setException(e.getClass().getSimpleName());
        response.setErrorCode(e.getMessage());
        response.setMessage(messageSource.getMessage(e.getMessage(), null, webRequest.getLocale()));

        log.error("error", e);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
