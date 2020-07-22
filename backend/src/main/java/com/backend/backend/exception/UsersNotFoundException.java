package com.backend.backend.exception;

public class UsersNotFoundException extends Exception {

    private  long user_id;
    public UsersNotFoundException(long user_id){
        super(String.format("Book is not found with id : '%s'", user_id));
    }
}
