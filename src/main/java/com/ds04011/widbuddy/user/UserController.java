package com.ds04011.widbuddy.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/view")
public class UserController {
	
	@GetMapping("/login")
	public String loginpage() {
		return "user/login";
	}
	
	@GetMapping("/register")
	public String registerpage() {
		return "user/register";
	}

}
