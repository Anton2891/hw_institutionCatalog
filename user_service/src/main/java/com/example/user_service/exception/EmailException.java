package com.example.user_service.exception;

public class EmailException extends Exception{
    private final String email;

    public EmailException(String email) {
        this.email = email;
    }
}
