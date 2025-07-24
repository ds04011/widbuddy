package com.ds04011.widbuddy.category.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.category.dto.CategoryDto;
import com.ds04011.widbuddy.category.repository.CategoryRepository;
import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.service.UserService;

import jakarta.persistence.PersistenceException;

@Service
public class CategoryService {
	
	private CategoryRepository categoryRepository;
	private UserService userService;
	public CategoryService (CategoryRepository categoryRepository, UserService userService) {
		this.categoryRepository = categoryRepository;
		this.userService = userService;
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
		List<CategoryDto> categoryDtoList = new ArrayList<>();	
		for(Category c : categoryList) {
			
			long id = c.getUserId();
			User user =  userService.getUserById(id);
			
			CategoryDto cd = CategoryDto.builder()
					.name(c.getName())
					.description(c.getDescription())
					.createdAt(c.getCreatedAt())
					.id(c.getId())
					.nickname(user.getNickname())
					.build();
			
			categoryDtoList.add(cd);
		}
		return categoryDtoList;
	}
	
	public Category getCategoryById(long id) {
		Optional<Category> opcate = categoryRepository.findById(id);
		Category cate = opcate.orElse(null);
		return cate;
	}

}
