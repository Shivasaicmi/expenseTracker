package com.expenseTracker.backend.controllers;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.expenseTracker.backend.entities.CategoriesEntity;
import com.expenseTracker.backend.models.ErrorResponse;
import com.expenseTracker.backend.services.CategoriesService;

import java.util.LinkedHashSet;

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
			categoriesService.updateCategories(categories);
			CategoriesEntity saveCategories = categoriesService.findCategoriesByUserId(categories.getUserId());
			return new ResponseEntity<>(saveCategories, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, "Unable to update categories"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{userid}")
	public ResponseEntity<?> getCategories(@PathVariable("userid") Long userId ){
		try{
			CategoriesEntity categories = categoriesService.findCategoriesByUserId(userId);
			return new ResponseEntity<>(categories.getCategories(),HttpStatus.OK);
		}
		catch (Exception exc){
			ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,exc.getMessage());
			return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/")
	public ResponseEntity<?> deleteCategory(@RequestBody CategoriesEntity categories){
		try{
			CategoriesEntity updatedCategories =  categoriesService.deleteCategories(categories);
			return new ResponseEntity<>(updatedCategories.getCategories(),HttpStatus.OK);
		}
		catch (Exception exe){
			ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,exe.getMessage());
			return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
