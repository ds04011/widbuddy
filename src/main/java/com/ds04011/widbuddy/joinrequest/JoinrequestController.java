package com.ds04011.widbuddy.joinrequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds04011.widbuddy.joinrequest.domain.Joinrequest;
import com.ds04011.widbuddy.joinrequest.service.JoinrequestService;

import jakarta.servlet.http.HttpSession;

@Controller
public class JoinrequestController {

	private JoinrequestService joinrequestService;
	public JoinrequestController(JoinrequestService joinrequestService) {
		this.joinrequestService = joinrequestService;
	}
	
	@PostMapping("/joinrequest/create")
	@ResponseBody
	public Map<String, String> createRequest(@RequestParam("joinflagId") long joinflagId
			, @RequestParam("description") String description
			, HttpSession session){
		
		long userId = (Long)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		
		if(joinrequestService.addRequest(joinflagId, userId, description)) {
			resultMap.put("result",  "success");
		} else {
			resultMap.put("result",  "fail");
		}
		return resultMap;
		
	}
	
	
	@PutMapping("/joinrequest/confirm")
	@ResponseBody
	public Map<String, String> acceptRequest(@RequestParam("joinrequestId") long joinrequestId
			, @RequestParam("state") String state){
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		// 객체 찾아와서 set 으로 값 할당 , 
		Joinrequest request =  joinrequestService.findById(joinrequestId);
		if (request == null) {
	        resultMap.put("result", "fail");
	        resultMap.put("message", "요청을 찾을 수 없습니다.");
	        return resultMap;
	    }

		request.setState(state);
		if(joinrequestService.saveRequest(request)) {
			resultMap.put("result",  "success");
		} else {
			resultMap.put("result",  "fail");
		}
		return resultMap;
	}
	
	
	@PutMapping("/joinrequest/deny")
	@ResponseBody
	public Map<String, String> denyRequest(@RequestParam("joinrequestId") long joinrequestId
			, @RequestParam("state") String state){
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		// 객체 찾아와서 set 으로 값 할당 , 
		Joinrequest request =  joinrequestService.findById(joinrequestId);
		if (request == null) {
	        resultMap.put("result", "fail");
	        resultMap.put("message", "요청을 찾을 수 없습니다.");
	        return resultMap;
	    }

		request.setState(state);
		if(joinrequestService.saveRequest(request)) {
			resultMap.put("result",  "success");
		} else {
			resultMap.put("result",  "fail");
		}
		return resultMap;
	}
	
	
	
	
	
	
}
