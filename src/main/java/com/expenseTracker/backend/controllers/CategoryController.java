package com.expenseTracker.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {


    

    @PutMapping("/")
    public ResponseEntity<?> updateCategories(){

    }

}
