package com.expenseTracker.backend.controllers;


import com.expenseTracker.backend.customExceptions.UserNameExistsException;
import com.expenseTracker.backend.entities.BudgetEntity;
import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.entities.UserEntity;
import com.expenseTracker.backend.repositories.CategoryRepository;
import com.expenseTracker.backend.services.BudgetService;
import com.expenseTracker.backend.services.CategoriesService;
import com.expenseTracker.backend.services.TransactionService;
import com.expenseTracker.backend.services.UserService;
import jakarta.transaction.Transactional;

import org.aspectj.weaver.NewConstructorTypeMunger;
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
    
    
	@PostMapping("/register")
	public ResponseEntity<?> userRegister(@RequestBody UserEntity user) {
		try {
			UserEntity savedUser=userService.registerUser(user);
			return new ResponseEntity<>(savedUser,HttpStatus.OK);
		}
		catch(Exception e) {
			ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		}
	}
  
  
  @PostMapping("/login")
  public ResponseEntity<?> userLogin(@RequestBody UserEntity user){

       try{
           UserEntity userFound = userService.userLogin(user);
            return new ResponseEntity<>(userFound, HttpStatus.OK);
        }
        catch (Exception e){
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND,e.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<?> getTransactionsByUserId(@PathVariable Long userId){

        try{
            List<TransactionEntity> transactions = transactionService.getTransactionsByUserId(userId);
            return new ResponseEntity<>(transactions,HttpStatus.OK);
        }
        catch (Exception exc){
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, exc.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        }
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

}
