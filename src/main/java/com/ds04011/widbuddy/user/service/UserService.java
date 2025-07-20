package com.ds04011.widbuddy.user.service;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	
	public void adduser(String email, String password, String nickname) {
		
		
		User user = User.builder().email(email).nickname(nickname)
				.password(password).build();
			
		 
	}

}
