package com.expenseTracker.backend.controllers;


import com.expenseTracker.backend.entities.BudgetEntity;
import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.services.BudgetService;
import com.expenseTracker.backend.services.TransactionService;
import com.expenseTracker.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.expenseTracker.backend.models.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService userService;
    private TransactionService transactionService;
    private BudgetService budgetService;

    @Autowired
    public UserController(UserService userService, TransactionService transactionService, BudgetService budgetService){
        this.userService = userService;
        this.transactionService = transactionService;
        this.budgetService = budgetService;
    }

    @GetMapping("budgets/{userId}")
	public ResponseEntity<?> getBudgetsByUserId(@PathVariable Long userId) {
		try {
			List<BudgetEntity> savedBudgets = budgetService.findByUserId(userId);
			return new ResponseEntity<>(savedBudgets,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
    
    @PostMapping("/transactions")
    public ResponseEntity<?> addPersonalTransaction(@RequestBody TransactionEntity transaction) {
    	try {
    		TransactionEntity savedTransaction = transactionService.addPersonalTransaction(transaction);
    		return new ResponseEntity<>(savedTransaction,HttpStatus.OK);
    	}
    	catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
		}
    }
    
    @DeleteMapping("/{userId}/budget/{category}")
    public ResponseEntity<?> deleteBudgetForUser(@PathVariable("userId") long userId,@PathVariable("category") String category) {
    	try {
    		budgetService.deleteBudgetForUser(userId, category);
    		return new ResponseEntity<>("Budget for category "+category+" for userId "+userId+" is deleted succesfully",HttpStatus.OK);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
    }

}
