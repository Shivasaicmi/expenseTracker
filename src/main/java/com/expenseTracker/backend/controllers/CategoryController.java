package com.expenseTracker.backend.controllers;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenseTracker.backend.entities.CategoriesEntity;
import com.expenseTracker.backend.models.ErrorResponse;
import com.expenseTracker.backend.services.CategoriesService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private CategoriesService categoriesService;

	@Autowired
	public CategoryController(CategoriesService categoriesService) {
		super();
		this.categoriesService = categoriesService;
	}

	@PutMapping("/")
	public ResponseEntity<?> updateCategories(@RequestBody CategoriesEntity categories) {
		try {
			System.out.println(categories.toString());
			categoriesService.updateCategories(categories);
			CategoriesEntity saveCategories = categoriesService.findCategoriesByUserId(categories.getUserId())[0];
			return new ResponseEntity<>(saveCategories, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, "Unable to update categories"),
					HttpStatus.BAD_REQUEST);
		}
	}

}
