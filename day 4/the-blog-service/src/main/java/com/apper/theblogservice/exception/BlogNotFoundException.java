package com.apper.theblogservice.exception;

public class BlogNotFoundException extends RuntimeException {

    public BlogNotFoundException(String message) {
        super(message);
    }
}