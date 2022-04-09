package com.oliveira.willy.starwarsresistence.exception;

public class UserAlreadExistsException extends RuntimeException {
    public UserAlreadExistsException(String message) {
        super(message);
    }
}
