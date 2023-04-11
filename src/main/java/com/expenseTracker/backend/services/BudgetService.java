package com.expenseTracker.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.expenseTracker.backend.customExceptions.CategoryNotFoundException;
import com.expenseTracker.backend.entities.BudgetEntity;
import com.expenseTracker.backend.models.ErrorResponse;
import com.expenseTracker.backend.repositories.BudgetRepository;

@Service
public class BudgetService {
	
	private BudgetRepository budgetRepository;
	private CategoriesService categoriesService;

	@Autowired
	public BudgetService(BudgetRepository budgetRepository, CategoriesService categoriesService) {
		this.budgetRepository = budgetRepository;
		this.categoriesService = categoriesService;
	}
	
	public BudgetEntity addBudget(BudgetEntity budget) throws Exception {
		if(categoriesService.isUserHasCategory(budget.getUserId(), budget.getCategory()))
			return budgetRepository.save(budget);
		else
			throw new CategoryNotFoundException("Category not found");
	}

}
