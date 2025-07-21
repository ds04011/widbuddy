package com.ds04011.widbuddy.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/post/view")
public class PostController {
	
	@GetMapping("/mainpage")
	public String mainpage() {
		return "post/mainpage";  
	}
		// layouts/mainpagedefault
		// post/mainpage
	
	@GetMapping("/allpost")
	public String allpostpage() {
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
