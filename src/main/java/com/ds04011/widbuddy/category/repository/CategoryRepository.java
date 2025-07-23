package com.ds04011.widbuddy.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.widbuddy.category.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	

}
