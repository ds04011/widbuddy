package com.ds04011.widbuddy.post;

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
import com.ds04011.widbuddy.comment.dto.CommentDto;
import com.ds04011.widbuddy.comment.service.CommentService;
import com.ds04011.widbuddy.joinflag.Service.JoinflagService;
import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.dto.PostDto;
import com.ds04011.widbuddy.post.dto.PostDtoAssembler;
import com.ds04011.widbuddy.post.service.PostService;

@Controller
@RequestMapping("/post/view")
public class PostController {
	
	private JoinflagService joinflagService;
	private CategoryDtoAssembler categoryDtoAssembler;
	private PostDtoAssembler postDtoAssembler;
	private PostService postService;
	private CommentService commentService;
	private CategoryService categoryService;
	public PostController(PostService postService
			, CommentService commentService
			, CategoryService categoryService
			, PostDtoAssembler postDtoAssembler
			, CategoryDtoAssembler categoryDtoAssembler
			, JoinflagService joinflagService) {
		this.postService = postService;
		this.commentService = commentService;
		this.categoryService = categoryService;
		this.postDtoAssembler = postDtoAssembler;
		this.categoryDtoAssembler = categoryDtoAssembler;
		this.joinflagService = joinflagService;
	}
	
	@GetMapping("/mainpage")
	public String mainpage(Model model) {
		
		
		List<Post> postList = postService.getAllPost();
		List<PostDto> postDtoList = postDtoAssembler.toDtoList(postList);
		
		List<Category> categoryList  = categoryService.getAllCategories();
		List<CategoryDto> categoryDtoList =   categoryDtoAssembler.toDtoList(categoryList);
		
		
		model.addAttribute("postList", postDtoList);
		model.addAttribute("categoryList", categoryDtoList);
		
		return "post/mainpage";  
	}
		
	
	@GetMapping("/allpost")
	public String allpostpage(Model model) {
		
		List<Post> postList = postService.getAllPost();
		List<PostDto> postDtoList = postDtoAssembler.toDtoList(postList);
		model.addAttribute("postList", postDtoList);
		
		
		return "post/allpost";
	}
	
	@GetMapping("/create")
	public String createPost(@RequestParam("categoryId") long categoryId
			, Model model) {
		
		model.addAttribute("categoryId", categoryId);
		return "post/input";
	}
	
	@GetMapping("/detail")
	public String postDetail(@RequestParam("postId") long postId
			, Model model) {
		
		Post post = postService.getPostById(postId);
		PostDto pd = postDtoAssembler.toDto(post);
		model.addAttribute("post", pd);
		
		List<CommentDto> cdList = commentService.getCommentByPostId(postId);
		model.addAttribute("comments", cdList);
		
		
		//joinflag Id 를 여기서 실어주자? dto 로 하는게 맞다? 컨트롤러로 받아서 서비스에서 처리하는 메서드가 맞다?
		long joinflagId = joinflagService.callJoinflagId(postId);
		model.addAttribute("joinflagId", joinflagId);
		
		
		return "post/postdetail";
		
		
	}
	
}
