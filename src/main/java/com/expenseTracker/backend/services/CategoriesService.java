package com.expenseTracker.backend.services;

import com.expenseTracker.backend.entities.CategoriesEntity;
import com.expenseTracker.backend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {

    private CategoryRepository categoryRepository;

    public CategoriesService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    // add a category

    public void addCategory(Long userId){
        categoryRepository.addCategory(userId);
    }

    // update a category list
    public void updateCategories(CategoriesEntity categories){
//        categoryRepository.appendCategories(categories.getUserId(),categories.getCategories());
        String[] category={"movies","food","shopping","travel"};
        categoryRepository.appendCategories(1L, category);
        System.out.println("after updating");
    }
    
    public CategoriesEntity[] getCategoriesByUserId(Long userId) {
    	return categoryRepository.findByUserId(userId);
    }

    // delete a category from categories list

    // get categories list

}
