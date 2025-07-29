package com.ds04011.widbuddy.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller

public class UserController {
	
	@GetMapping("/user/view/login")
	public String loginpage() {
		return "user/login";
	}
	
	@GetMapping("/user/view/register")
	public String registerpage() {
		return "user/register";
	}
	
	@GetMapping("/user/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session =  request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("nickname");
		
		return "redirect:/user/view/login";
		
		
	}
	
	

}
