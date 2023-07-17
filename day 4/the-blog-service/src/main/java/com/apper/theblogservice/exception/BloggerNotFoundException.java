package com.apper.theblogservice.exception;

public class BloggerNotFoundException extends RuntimeException {

    public BloggerNotFoundException(String message) {
        super(message);
    }
}