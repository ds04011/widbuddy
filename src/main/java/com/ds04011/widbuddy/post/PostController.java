package com.ds04011.widbuddy.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ds04011.widbuddy.comment.dto.CommentDto;
import com.ds04011.widbuddy.comment.service.CommentService;
import com.ds04011.widbuddy.post.dto.PostDto;
import com.ds04011.widbuddy.post.service.PostService;

@Controller
@RequestMapping("/post/view")
public class PostController {
	
	private PostService postService;
	private CommentService commentService;
	public PostController(PostService postService
			, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}
	
	@GetMapping("/mainpage")
	public String mainpage() {
		return "post/mainpage";  
	}
		
	
	@GetMapping("/allpost")
	public String allpostpage(Model model) {
		
		
		List<PostDto> postList = postService.getAllPostDto();
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
		
		PostDto pd = postService.getPostDtoById(postId); 
		model.addAttribute("post", pd);
		
		List<CommentDto> cdList = commentService.getCommentByPostId(postId);
		model.addAttribute("comments", cdList);
		
		return "post/postdetail";
		
		
	}
	
}
