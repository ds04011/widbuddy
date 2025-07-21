package com.ds04011.widbuddy.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserRestController {
	
	private UserService userService;
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	
	
	@PostMapping("/user/register")
	public Map<String, String> register(@RequestParam("email") String email
			, @RequestParam("password") String password
			, @RequestParam("nickname") String nickname) {
		
		
		
		Map<String, String> resultMap = new HashMap<>();
		if( userService.adduser(email, password, nickname)) {
			resultMap.put("result",  "success");
		} else {
			resultMap.put("result",  "fail");
		}
		return resultMap;	
	}
	
	@GetMapping("/user/email/duplicate")
	public Map<String, Boolean> checkemail(@RequestParam("email") String email){
		boolean result = userService.emailCheck(email);
		Map<String, Boolean> resultMap = new HashMap<>();
//		if(result) {
//			resultMap.put("duplicate", "yes");
//		} else {
//			resultMap.put("duplicate", "no");
//		}
//		return resultMap;
		resultMap.put("duplicate",  result);
		return resultMap;
	}
	
	@GetMapping("/user/nickname/duplicate")
	public Map<String, Boolean> checknickname(@RequestParam("nickname") String nickname){
		boolean result = userService.nicknameCheck(nickname);
		Map<String, Boolean> resultMap = new HashMap<>();
		resultMap.put("duplicate",  result);
		
		return resultMap;
	}
	
	@PostMapping("/user/login")
	public Map<String, String> login(@RequestParam("email") String email, 
		@RequestParam("password") String password
		, HttpServletRequest request){
		
		
		
		User user = userService.login(email, password);
		Map<String, String> resultMap = new HashMap<>();
		
		if(user !=null) {
			resultMap.put("result", "success");
			
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId()); // 로그인판별
			session.setAttribute("nickname", user.getNickname());
			
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	@GetMapping("/user/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session =  request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("nickname");
		
		return "redirect:/user/view/login";
		
		
	}

}
