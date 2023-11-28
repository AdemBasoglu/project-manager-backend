package be.intecbrussel.projectmanagerbackend.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProjectManagerException.class)
    public ResponseEntity<ErrorResponse> onDataNotFound(DataNotFoundException exception,
                                                        HttpServletRequest request) {
        return getErrorResponseEntity(HttpStatus.BAD_REQUEST, request, exception.getMessage());
    }

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
