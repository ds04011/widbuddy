package com.ds04011.widbuddy.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.category.repository.CategoryRepository;

import jakarta.persistence.PersistenceException;

@Service
public class CategoryService {
	
	private CategoryRepository categoryRepository;
	public CategoryService (CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	
	public boolean addCategory(String name, String description, long userId) {
		
		Category category = Category.builder().name(name)
				.description(description)
				.userId(userId)
				.build();
		try {
			categoryRepository.save(category);
		} catch(PersistenceException e) {
			return false;
		}
		return true;
	}
	
	public List<Category> getAllCategories(){
		 return categoryRepository.findAll();
	}

}
