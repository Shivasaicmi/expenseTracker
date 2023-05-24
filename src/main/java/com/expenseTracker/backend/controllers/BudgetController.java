package com.expenseTracker.backend.controllers;

import java.util.List;

import com.expenseTracker.backend.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenseTracker.backend.customExceptions.CategoryNotFoundException;
import com.expenseTracker.backend.entities.BudgetEntity;
import com.expenseTracker.backend.models.ErrorResponse;
import com.expenseTracker.backend.repositories.BudgetRepository;
import com.expenseTracker.backend.services.BudgetService;

@RestController
@RequestMapping("/budget")
public class BudgetController {
	
	private BudgetService budgetService;

	@Autowired
	public BudgetController(BudgetService budgetService) {
		this.budgetService = budgetService;
	}
	
	@PostMapping("/")
	public ResponseEntity<?> addBudget(@RequestBody BudgetEntity budget, Authentication authenticationObject) {
		UserEntity authenticatedUser = (UserEntity)authenticationObject.getPrincipal();
		budget.setUserId(authenticatedUser.getUserId());
		try {
			BudgetEntity savedBudget = budgetService.addBudget(budget);
			return new ResponseEntity<>(savedBudget,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<?> updateTotalBudget(@RequestBody BudgetEntity budget, Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		try {
			BudgetEntity savedBudget = budgetService.updateTotalBudget(user.getUserId(), budget.getCategory(), budget.getTotalBudget());
			return new ResponseEntity<>(savedBudget,HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/refresh")
	public ResponseEntity<?> refreshBudget(@RequestBody BudgetEntity budget, Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		try {
			List<BudgetEntity> savedBudgets = budgetService.refreshBudget(user.getUserId());
			return new ResponseEntity<>(savedBudgets,HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
