package com.ds04011.widbuddy.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.service.PostService;

@Controller
@RequestMapping("/post/view")
public class PostController {
	
	private PostService postService;
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/mainpage")
	public String mainpage() {
		return "post/mainpage";  
	}
		
	
	@GetMapping("/allpost")
	public String allpostpage(Model model) {
		
		
		List<Post> postList = postService.getAllPost();
		model.addAttribute("postList", postList);
		
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
		
		model.addAttribute("postId", postId);
		return "post/detail";
		
		
	}
	
}
