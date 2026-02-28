package com.mari.moviereviews.resources.exception;

import com.mari.moviereviews.services.exception.ObjectNotFoundException;
import com.mari.moviereviews.services.exception.UnavailableUsernameException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Not Found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UnavailableUsernameException.class)
    public ResponseEntity<StandardError> unavailableUsername(UnavailableUsernameException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Duplicate username", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
