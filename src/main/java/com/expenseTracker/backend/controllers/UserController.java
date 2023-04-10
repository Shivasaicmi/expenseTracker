package com.expenseTracker.backend.controllers;


import com.expenseTracker.backend.customExceptions.UserNameExistsException;
import com.expenseTracker.backend.entities.UserEntity;
import com.expenseTracker.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.expenseTracker.backend.models.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {



  private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    
    
	@PostMapping("/register")
	public ResponseEntity<?> userRegister(@RequestBody UserEntity user) {
		try {
			UserEntity savedUser=userService.registerUser(user);
			return new ResponseEntity<>(savedUser,HttpStatus.OK);
		}
		catch(Exception e) {
			ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST,"Username already exists");
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


}
