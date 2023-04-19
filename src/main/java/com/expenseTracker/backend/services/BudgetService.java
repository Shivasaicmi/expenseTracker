package com.expenseTracker.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.expenseTracker.backend.customExceptions.BudgetAlreadyFoundException;
import com.expenseTracker.backend.customExceptions.BudgetNotFoundException;
import com.expenseTracker.backend.customExceptions.CategoryNotFoundException;
import com.expenseTracker.backend.customExceptions.UserNotFoundException;
import com.expenseTracker.backend.entities.BudgetEntity;
import com.expenseTracker.backend.models.ErrorResponse;
import com.expenseTracker.backend.repositories.BudgetRepository;

import jakarta.transaction.Transactional;

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
		budget.setExpenditure(0);
		if(categoriesService.isUserHasCategory(budget.getUserId(), budget.getCategory()))
		{
			if(!findByUserIdAndCategory(budget.getUserId(), budget.getCategory())) {
				return budgetRepository.save(budget);
			}
			else {
				throw new BudgetAlreadyFoundException("Budget already found under userId "+budget.getUserId());
			}
		}
		else
			throw new CategoryNotFoundException("Category not found");
	}
	
	@Transactional
	public BudgetEntity addExpense(long userId,String category,long expenditure) throws CategoryNotFoundException {
		if(categoriesService.isUserHasCategory(userId, category)) {
			budgetRepository.addExpense(userId, category, expenditure);
			BudgetEntity savedBudget = budgetRepository.findByUserIdAndCategory(userId, category);
			return savedBudget;
		}
		else {
			throw new CategoryNotFoundException("Category not found");
		}
	}
	
	@Transactional
	public BudgetEntity updateTotalBudget(long userId, String category, long totalBudget) throws CategoryNotFoundException {
		if(categoriesService.isUserHasCategory(userId, category)) {
			budgetRepository.updateTotalBudget(userId, category, totalBudget);
			BudgetEntity savedBudget = budgetRepository.findByUserIdAndCategory(userId, category);
			return savedBudget;
		}
		else {
			throw new CategoryNotFoundException("Category not found");
		}
	}
	
	@Transactional
	public List<BudgetEntity> refreshBudget(long userId) {
		budgetRepository.refreshBudget(userId);
		List<BudgetEntity> savedBudgets = budgetRepository.findByUserId(userId);
		return savedBudgets;
	}
	
	public List<BudgetEntity> findByUserId(long userId) throws UserNotFoundException {
		List<BudgetEntity> savedBudgets = budgetRepository.findByUserId(userId);
		if(savedBudgets.size()==0)
			throw new UserNotFoundException("User not found with id "+userId);
		return savedBudgets;
	}
	
	public boolean findByUserIdAndCategory(long userId, String category) {
		BudgetEntity savedBudget = budgetRepository.findByUserIdAndCategory(userId, category);
		if(savedBudget != null)
			return true;
		else 
			return false;
	}
	
	public void deleteBudgetForUser(long userId, String category) throws BudgetNotFoundException {
		BudgetEntity savedBudget = budgetRepository.findByUserIdAndCategory(userId, category);
		System.out.println(savedBudget);
		if(savedBudget!=null) {
			budgetRepository.delete(savedBudget);
		}
		else {
			throw new BudgetNotFoundException("Budget for catgeory "+category+" not found for userId "+userId);
		}
	}

}
