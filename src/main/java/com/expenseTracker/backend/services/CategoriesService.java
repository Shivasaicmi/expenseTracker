package com.expenseTracker.backend.services;

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
    public String[] updateCategories(Long userId,String[] newCategories){
        String[] categories =  categoryRepository.appendCategories(userId,newCategories);
        return categories;
    }

    // delete a category from categories list

    // get categories list

}
