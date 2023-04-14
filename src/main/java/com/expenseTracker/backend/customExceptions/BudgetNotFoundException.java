package com.expenseTracker.backend.customExceptions;

public class BudgetNotFoundException extends Exception {

	public BudgetNotFoundException(String message) {
		super(message);
	}
}
