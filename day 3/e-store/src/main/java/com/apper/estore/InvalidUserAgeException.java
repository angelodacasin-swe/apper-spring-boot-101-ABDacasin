package com.apper.estore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserAgeException extends RuntimeException {

    public InvalidUserAgeException(String message) {
        super(message);
    }
}