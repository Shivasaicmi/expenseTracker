package com.expenseTracker.backend.controllers;


import com.expenseTracker.backend.entities.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {



    @PostMapping("/")
    public UserEntity userLogin(){
        
    }

}
