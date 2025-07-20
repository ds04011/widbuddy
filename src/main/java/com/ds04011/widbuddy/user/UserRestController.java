package com.ds04011.widbuddy.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
	
	@PostMapping("/user/register")
	public String register(@RequestParam("email") String email
			, @RequestParam("password") String password
			, @RequestParam("nickname") String nickname) {
		
		
		
	}

}
