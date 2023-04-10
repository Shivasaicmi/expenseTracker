package com.expenseTracker.backend.controllers;


import com.expenseTracker.backend.customExceptions.UserNameExistsException;
import com.expenseTracker.backend.entities.TransactionEntity;
import com.expenseTracker.backend.entities.UserEntity;
import com.expenseTracker.backend.repositories.CategoryRepository;
import com.expenseTracker.backend.services.CategoriesService;
import com.expenseTracker.backend.services.TransactionService;
import com.expenseTracker.backend.services.UserService;
import jakarta.transaction.Transactional;
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



    @Autowired
    public UserController(UserService userService, TransactionService transactionService){
        this.userService = userService;
        this.transactionService = transactionService;
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

    @GetMapping("/transaction/{userId}")
    public ResponseEntity<?> getTransactionByUserId(@PathVariable Long userId){

        try{
            List<TransactionEntity> transactions = transactionService.getTransactionsByUserId(userId);
            return new ResponseEntity<>(transactions,HttpStatus.OK);
        }
        catch (Exception exc){
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, exc.getMessage());
            return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        }

    }

}
