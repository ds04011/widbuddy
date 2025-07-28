package com.ds04011.widbuddy.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ds04011.widbuddy.common.FileManager;
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
			, @RequestParam(value = "imagePath", required = false) String imagePath 
			// 서머노트는 이미지를 올리는 메서드가 별도로 필요하대, 
			// 이미 저장은 끝났고, imagePath 만 받아와서 post  의 컬럼 값으로 저장이 되면 되니까, String 으로 받아오자. 
			, HttpSession session) {
		
		long userId = (Long)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.addPost(userId, title, contents, categoryId, imagePath)) { // imagePath 추가
			resultMap.put("result",  "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;	
	}
	
	@PostMapping("/post/uploadimage")
	public String uploadImage(@RequestParam("file") MultipartFile file
			, HttpSession session) {
		
		long userId = (Long)session.getAttribute("userId");
		
		String imagePath = FileManager.saveFile(userId, file);
		
		 if (imagePath != null) {
		        return imagePath; 
		    } else {
		        return "업로드 실패";
		    }
		 //http://localhost:8080/images/1_1753706704293/test1.jpg

	}
	
	
	

}
