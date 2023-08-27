package com.barisdev.Url.Shortener.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class DateExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDateParseException(DateTimeParseException ex) {
        return "Invalid date format. Date format:yyyy-MM-dd";
    }

    @ExceptionHandler(PastDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlePastDateException(PastDateException ex) {
        return "The entered date cannot be a date prior to today.";
    }

    public static class PastDateException extends RuntimeException {
        public PastDateException() {
            super();
        }
    }
}

