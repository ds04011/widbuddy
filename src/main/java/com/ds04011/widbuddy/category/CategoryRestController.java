package com.ds04011.widbuddy.category;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds04011.widbuddy.category.service.CategoryService;

import jakarta.servlet.http.HttpSession;

@RestController
public class CategoryRestController {
	
	private CategoryService categoryService ;
	public CategoryRestController(CategoryService categoryService ) {
		this.categoryService = categoryService;
	}
	
	@PostMapping("/category/create")
	public Map<String, String> categoryCreate(@RequestParam("name") String name
			,@RequestParam("description") String description 
			, HttpSession session){
		
		long userId = (Long)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		
		
		if(categoryService.addCategory(name, description, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result",  "fail");
		}
		return resultMap;

		
		
		
		
	}

}
