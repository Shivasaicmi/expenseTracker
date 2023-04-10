package com.expenseTracker.backend.customExceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message){
        super(message);
    }

}
