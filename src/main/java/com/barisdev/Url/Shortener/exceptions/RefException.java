package com.barisdev.Url.Shortener.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RefException {

    @ExceptionHandler(SameRefException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleSameRefException(SameRefException ex){
        return "This ref is already exist";
    }

    public static class SameRefException extends RuntimeException{
        public SameRefException(){
            super();
        }
    }

}
