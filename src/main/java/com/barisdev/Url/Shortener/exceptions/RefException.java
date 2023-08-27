package com.barisdev.Url.Shortener.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RefException {

    @ExceptionHandler(sameRefException.class)
    public ResponseEntity<String> handleSameRefException(sameRefException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(RefNotFoundException.class)
    public ResponseEntity<String> RefNotFoundException(RefNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public static class sameRefException extends RuntimeException {
        public sameRefException(String ref) {
            super("This ref: " + ref + " is already exist");
        }
    }

    public static class RefNotFoundException extends RuntimeException {
        public RefNotFoundException(String ref) {
            super("This ref: " + ref + " is not exist");
        }
    }



}

// iki kod parçacığı arasındaki fark?

