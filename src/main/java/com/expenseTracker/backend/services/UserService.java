package com.expenseTracker.backend.services;

import com.expenseTracker.backend.customExceptions.UserNameExistsException;
import com.expenseTracker.backend.customExceptions.UserNotFoundException;
import com.expenseTracker.backend.entities.UserEntity;
import com.expenseTracker.backend.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    //register the user
    public UserEntity registerUser(UserEntity user) throws UserNameExistsException {
    	UserEntity existingUser = this.getUserByUsername(user.getUserName());
    	if(existingUser!=null) {
			throw new UserNameExistsException("Username already found");
		}
    	UserEntity savedUser=userRepository.save(user);
    	return savedUser;
    }

    // login the user
    public UserEntity userLogin(UserEntity user) throws UserNotFoundException {
       Optional<UserEntity> result =  userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword());
       if(result.isPresent()){
           return result.get();
       }
       else{
           throw new UserNotFoundException("Wrong credentials");
       }
    }
    
    //get the user by username
    public UserEntity getUserByUsername(String username) {
 	   Optional<UserEntity> user = userRepository.findByUserName(username);

 	   if(user.isPresent())
 		   return user.get();
 	   else
 		   return null;
    }
}
