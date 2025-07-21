package com.ds04011.widbuddy.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds04011.widbuddy.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
public class PostRestController {
	
	private PostService postService;
	public PostRestController(PostService postService) { 
		this.postService = postService;
	}
	
	
	@PostMapping("/post/create")
	public Map<String, String> postcreate(@RequestParam("title") String title
			, @RequestParam("contents") String contents
			, @RequestParam("categoryId") long categoryId
			//, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile 
			, HttpSession session) {
		
		long userId = (Long)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.addPost(userId, title, contents, categoryId)) { // imageFile 추가
			resultMap.put("result",  "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
		
	}
	
	

}
