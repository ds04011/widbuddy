package com.ds04011.widbuddy.joinflag;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds04011.widbuddy.joinflag.Service.JoinflagService;

import jakarta.servlet.http.HttpSession;

@Controller
public class JoinflagController {
	
	private JoinflagService joinflagService;
	public JoinflagController(JoinflagService joinflagService) {
		this.joinflagService = joinflagService;
	}
	
//	@PostMapping("/joinflag/create")
//	@ResponseBody
//	public Map<String, String> createjoinflag(@RequestParam("postId") long postId
//			, @RequestParam("headcount") int headcount
//			, HttpSession session){
//		
//		long userId = (Long)session.getAttribute("userId");
//		Map<String, String> resultMap = new HashMap<>();
//		
//		if(joinflagService.addJoinflag(userId, postId, headcount)) {
//			resultMap.put("result", "success");
//		} else {
//			resultMap.put("result", "fail");
//		}
//		return resultMap;
//	}
	
	
}
