package com.mari.moviereviews.services.exception;

public class UnavailableUsernameException extends RuntimeException {
    public UnavailableUsernameException(String message) {
        super(message);
    }
}
