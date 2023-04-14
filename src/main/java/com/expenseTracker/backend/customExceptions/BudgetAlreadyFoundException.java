package com.expenseTracker.backend.customExceptions;

public class BudgetAlreadyFoundException extends Exception {

	public BudgetAlreadyFoundException(String message) {
		super(message);
	}
}
