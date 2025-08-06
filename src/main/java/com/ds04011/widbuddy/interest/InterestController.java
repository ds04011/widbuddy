package com.ds04011.widbuddy.interest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds04011.widbuddy.interest.service.InterestService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class InterestController {
	
	private final InterestService interestService;
	
	@PostMapping("/interest/create")
	public Map<String, String> addInterest(HttpSession session
			, @RequestParam("categoryId")long categoryId){
		
		long userId = (Long) session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		
		if( interestService.addInterest( userId,  categoryId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	@DeleteMapping("/interest/delete")
	public Map<String, String> deleteInterest(HttpSession session
			, @RequestParam("categoryId")long categoryId){
		
		long userId = (Long) session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		
		
		if( interestService.deleteInterest( userId,  categoryId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}

}
