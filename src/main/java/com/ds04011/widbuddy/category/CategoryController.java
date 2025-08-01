package com.ds04011.widbuddy.category;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.category.dto.CategoryDto;
import com.ds04011.widbuddy.category.dto.CategoryDtoAssembler;
import com.ds04011.widbuddy.category.service.CategoryService;
import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.dto.PostDto;
import com.ds04011.widbuddy.post.dto.PostDtoAssembler;
import com.ds04011.widbuddy.post.service.PostService;

@Controller
@RequestMapping("/category/view")
public class CategoryController {
	
	private CategoryDtoAssembler categoryDtoAssembler;
	private PostDtoAssembler postDtoAssembler;
	private CategoryService categoryService ;
	private PostService postService;
	public CategoryController(CategoryService categoryService
			, PostService postService
			, PostDtoAssembler postDtoAssembler
			, CategoryDtoAssembler categoryDtoAssembler) {
		this.categoryService = categoryService ;
		this.postService = postService;
		this.postDtoAssembler = postDtoAssembler;
		this.categoryDtoAssembler = categoryDtoAssembler;
	}
	
	@GetMapping("/allcategory")
	public String allcategory(Model model) {
		
		
		List<Category> categoryList = categoryService.getAllCategories();
		List<CategoryDto> categoryDtoList = categoryDtoAssembler.toDtoList(categoryList); 
		model.addAttribute("categoryList", categoryDtoList);
		
		return "category/allcategory";
	}
	
	@GetMapping("/allpost")
	public String allpost(@RequestParam("categoryId") long categoryId,
			Model model) {
		
		// 카테고리정보를 미리 보내서 해당하는 카테고리에 속하는 글만 가져와야 함. 
		// DTO 로 모델에 실어야함.
		Category c = categoryService.getCategoryById(categoryId);
		String name = c.getName();
		
		List<Post>  postList = postService.getPostByCategoryId(categoryId);
		List<PostDto> postDtoList = postDtoAssembler.toDtoList(postList);
		
		
		model.addAttribute("postList", postDtoList);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("categoryName", name);
		
		return "category/categoryposts";
	}
	
	@GetMapping("/create")
	public String createCategory() {
		return "category/input";
	}
	
}
