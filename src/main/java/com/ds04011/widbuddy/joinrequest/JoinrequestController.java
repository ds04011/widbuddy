package com.ds04011.widbuddy.joinrequest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds04011.widbuddy.joinrequest.service.JoinrequestService;

import jakarta.servlet.http.HttpSession;

@Controller
public class JoinrequestController {

	private JoinrequestService joinrequestService;
	public JoinrequestController(JoinrequestService joinrequestService) {
		this.joinrequestService = joinrequestService;
	}
	
	@PostMapping("/joinreqeust/create")
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
}
