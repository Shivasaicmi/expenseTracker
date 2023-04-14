package com.expenseTracker.backend.customExceptions;

public class CategoryNotFoundException extends Exception {
	
	public CategoryNotFoundException(String message) {
		super(message);
	}
}
