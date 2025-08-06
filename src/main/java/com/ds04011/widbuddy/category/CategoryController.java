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
import com.ds04011.widbuddy.interest.service.InterestService;
import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.dto.PostDto;
import com.ds04011.widbuddy.post.dto.PostDtoAssembler;
import com.ds04011.widbuddy.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/category/view")
public class CategoryController {
	
	private InterestService interestService;
	private CategoryDtoAssembler categoryDtoAssembler;
	private PostDtoAssembler postDtoAssembler;
	private CategoryService categoryService ;
	private PostService postService;
	public CategoryController(CategoryService categoryService
			, PostService postService
			, PostDtoAssembler postDtoAssembler
			, CategoryDtoAssembler categoryDtoAssembler
			, InterestService interestService) {
		this.categoryService = categoryService ;
		this.postService = postService;
		this.postDtoAssembler = postDtoAssembler;
		this.categoryDtoAssembler = categoryDtoAssembler;
		this.interestService = interestService;
	}
	
	@GetMapping("/allcategory")
	public String allcategory(Model model, HttpSession session) {
		
		long userId = (Long)session.getAttribute("userId");
		List<Category> categoryList = categoryService.getAllCategories();
		List<CategoryDto> categoryDtoList = categoryDtoAssembler.toCateDtoList(categoryList, userId); 
		model.addAttribute("categoryList", categoryDtoList);
		
		return "category/allcategory";
	}
	
	@GetMapping("/allpost")
	public String allpost(@RequestParam("categoryId") long categoryId
			, HttpSession session
			, Model model) {
		
		// 카테고리정보를 미리 보내서 해당하는 카테고리에 속하는 글만 가져와야 함. 
		// DTO 로 모델에 실어야함.
		Category c = categoryService.getCategoryById(categoryId);
		String name = c.getName();
		
		List<Post>  postList = postService.getPostByCategoryId(categoryId);
		List<PostDto> postDtoList = postDtoAssembler.toDtoList(postList);
		
		long userId = (Long) session.getAttribute("userId");
		// 관심정보,  userId, categoryId 기반으로 interest 가져와서, 해당 interest 의 isInterest 를  
		int isInterest = interestService.isInterest(userId, categoryId); // 1 = 관심, 0 = ㄴㄴ
		
		model.addAttribute("isInterest", isInterest);
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
