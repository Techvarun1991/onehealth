package com.onehealth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for handling DatabaseException.
 * This exception is thrown when there's an issue accessing the database or performing database operations.
 * It returns a 422 (UNPROCESSABLE_ENTITY) HTTP status when caught by the controller advice.
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class DatabaseException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor to create a new DatabaseException with the given error message.
     *
     * @param message The error message to be displayed.
     */
    public DatabaseException(String message) {
        super(message);
    }
}
