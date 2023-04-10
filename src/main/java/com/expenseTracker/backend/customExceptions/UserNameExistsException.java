package com.expenseTracker.backend.customExceptions;

public class UserNameExistsException extends Exception {
	
	public UserNameExistsException(String message) {
		super(message);
	}
}
