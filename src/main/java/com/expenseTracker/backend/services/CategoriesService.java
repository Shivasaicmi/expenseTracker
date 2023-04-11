package com.expenseTracker.backend.services;

import com.expenseTracker.backend.entities.CategoriesEntity;
import com.expenseTracker.backend.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {

	private CategoryRepository categoryRepository;

	@Autowired
	public CategoriesService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	// add a category

	public void addCategory(Long userId) {
		categoryRepository.addCategory(userId);
	}

	@Transactional
	public CategoriesEntity deleteCategories(CategoriesEntity categories){
		this.updateCategories(categories);
		return this.findCategoriesByUserId(categories.getUserId());
	}

	// update a category list
	@Transactional
	public void updateCategories(CategoriesEntity categories) {
		categoryRepository.updateCategories(categories.getUserId(),
				categories.getCategories().toArray(new String[categories.getCategories().size()]));
	}

	public CategoriesEntity findCategoriesByUserId(Long userId) {
		return categoryRepository.findByUserId(userId);
	}


}
