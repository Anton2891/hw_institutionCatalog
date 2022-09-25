package com.example.user_service.exception;

public class UserNotFoundException extends Exception{
    private final Long id;
    private final String email;


    public UserNotFoundException(Long id) {
        this.id = id;
        this.email = null;
    }
    public UserNotFoundException(String email){
        this.email = email;
        this.id = null;
    }
}
