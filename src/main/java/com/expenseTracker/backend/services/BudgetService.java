package com.expenseTracker.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenseTracker.backend.entities.BudgetEntity;
import com.expenseTracker.backend.repositories.BudgetRepository;

@Service
public class BudgetService {
	
	private BudgetRepository budgetRepository;

	@Autowired
	public BudgetService(BudgetRepository budgetRepository) {
		this.budgetRepository = budgetRepository;
	}
	
	public BudgetEntity addBudget(BudgetEntity budget) {
		return budgetRepository.save(budget);
	}

}
