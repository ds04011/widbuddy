package com.ds04011.widbuddy.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds04011.widbuddy.comment.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
public class CommentController {
	
	private CommentService commentService;
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/comment/create")
	public Map<String, String> commentcreate(HttpSession session
			, @RequestParam("contents") String contents
			, @RequestParam("postId") long postId){
		long userId = (Long)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(commentService.addComment(userId, postId, contents)) {
			resultMap.put("result",  "success");
		} else {
			resultMap.put("result",  "fail");
		}
		return resultMap;
		
	}
	
//	@DeleteMapping("/comment/delete")
//	public Map<String, String> commentdelete(){
//		
//	}
	
	
}
