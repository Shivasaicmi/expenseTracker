package com.expenseTracker.backend.controllers;


import com.expenseTracker.backend.customExceptions.UserNameExistsException;
import com.expenseTracker.backend.entities.UserEntity;
import com.expenseTracker.backend.services.UserService;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> userRegister(@RequestBody UserEntity user) {
		UserEntity existingUser = userService.getUserByUsername(user.getUserName());
		if(existingUser==null) {
			UserEntity savedUser=userService.registerUser(user);
			return new ResponseEntity<>(savedUser,HttpStatus.OK);
		}
		else {
//			return new ResponseEntity<>(new UserNameExistsException("Username already exists"),HttpStatus.BAD_REQUEST );
			return new ResponseEntity<>("Username already exists",HttpStatus.BAD_REQUEST);
		}
	}

}
