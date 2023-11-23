package com.onehealth.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.onehealth.dto.ErrorMessageDTO;


/**	
 * GlobalExceptionHandler is a centralized exception handling class for the application.
 * It uses the @ControllerAdvice annotation to handle exceptions globally across all controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Exception handler for DatabaseException.
     *
     * @param ex      The DatabaseException that occurred.
     * @param request The current WebRequest.
     * @return ResponseEntity containing the error details and HTTP status.
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<?> DatabaseException(DatabaseException ex, WebRequest request) {
        ErrorMessageDTO errorDetails = new ErrorMessageDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> ResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessageDTO errorDetails = new ErrorMessageDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    
    @ExceptionHandler(ServiceNotAvailableException.class)
    public ResponseEntity<?> ServiceNotAvailableException(ServiceNotAvailableException ex, WebRequest request) {
        ErrorMessageDTO errorDetails = new ErrorMessageDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
