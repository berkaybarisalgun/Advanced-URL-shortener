package com.barisdev.Url.Shortener.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IdException {

    @ExceptionHandler
    public ResponseEntity<String> handleIdNotFoundException(idNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


        public static class idNotFoundException extends RuntimeException{
            public idNotFoundException(Long id){
                super("This id:"+String.valueOf(id)+" is not exist");
            }
        }

}
