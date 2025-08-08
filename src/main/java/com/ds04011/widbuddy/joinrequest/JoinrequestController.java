package com.ds04011.widbuddy.joinrequest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds04011.widbuddy.joinflag.Service.JoinflagService;
import com.ds04011.widbuddy.joinflag.domain.Joinflag;
import com.ds04011.widbuddy.joinrequest.domain.Joinrequest;
import com.ds04011.widbuddy.joinrequest.service.JoinrequestService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class JoinrequestController {

	private JoinrequestService joinrequestService;
	private JoinflagService joinflagService;
	public JoinrequestController(JoinrequestService joinrequestService, JoinflagService joinflagService) {
		this.joinrequestService = joinrequestService;
		this.joinflagService = joinflagService;
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
	@Transactional
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
			
			// 요청에 대한 응답이 수락 -> 해당 joinflag 의 currentNubmer + 1 해야함. 
			// 해당 객체를 찾아와서, 수정하자. 
			Joinflag flag = joinflagService.findById(request.getJoinFlagId());
			flag.setCurrentNumber(flag.getCurrentNumber() + 1);
			joinflagService.saveFlag(flag);
			
		} else {
			resultMap.put("result",  "fail");
		}
		
		
		
		return resultMap;
	}
	
	
	@PutMapping("/joinrequest/deny")
	@ResponseBody
	@Transactional
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
