package com.ds04011.widbuddy.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post/view")
public class PostController {
	
	@GetMapping("/mainpage")
	public String mainpage() {
		return "post/mainpage";  
	}
		// layouts/mainpagedefault
		// post/mainpage
	
	
}
