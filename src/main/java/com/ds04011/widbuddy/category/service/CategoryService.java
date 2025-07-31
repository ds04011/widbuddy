package com.ds04011.widbuddy.category.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.category.CategoryDtoAssembler;
import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.category.dto.CategoryDto;
import com.ds04011.widbuddy.category.repository.CategoryRepository;
import com.ds04011.widbuddy.user.service.UserService;

import jakarta.persistence.PersistenceException;

@Service
public class CategoryService {
	
	
	private CategoryDtoAssembler categoryDtoAssembler;
	private CategoryRepository categoryRepository;
	public CategoryService (CategoryRepository categoryRepository
			, CategoryDtoAssembler categoryDtoAssembler) {
		this.categoryRepository = categoryRepository;
		this.categoryDtoAssembler = categoryDtoAssembler;
		
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
	
	public List<CategoryDto> getAllCategorDto(){
		
		List<Category> categoryList = categoryRepository.findAll();
		List<CategoryDto> dtoList = categoryDtoAssembler.toDtoList(categoryList);
		return dtoList;
		
		
	}
	
	public Category getCategoryById(long id) {
		Optional<Category> opcate = categoryRepository.findById(id);
		Category cate = opcate.orElse(null);
		return cate;
	}

}
