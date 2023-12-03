package be.intecbrussel.projectmanagerbackend.exceptions;

import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

// @RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler /* extends ResponseEntityExceptionHandler  */ {
    @ExceptionHandler(ProjectManagerException.class)
    public ResponseEntity<ErrorResponse> onDataNotFound(DataNotFoundException exception,
                                                        HttpServletRequest request) {
        return getErrorResponseEntity(HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> onConstraintValidationException(ConstraintViolationException exception,
                                                                         HttpServletRequest request) {

        return getErrorResponseEntity(HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> onMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                           HttpServletRequest request) {

        return getErrorResponseEntity(HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> onIllegalArgumentException(IllegalArgumentException exception,
                                                                    HttpServletRequest request) {
        return getErrorResponseEntity(HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ErrorResponse> handleException(Exception exception,
    //                                                      HttpServletRequest request) {
    //
    //     return getErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, request, exception.getMessage());
    // }


    private static ResponseEntity<ErrorResponse> getErrorResponseEntity(
            HttpStatus badRequest,
            HttpServletRequest request,
            String exception
    ) {
        HttpStatus status = badRequest;
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                request.getRequestURI(),
                status.name(),
                exception);

        return new ResponseEntity<>(errorResponse, status);
    }

}
