package com.ds04011.widbuddy.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.common.HashEncoder;
import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.repository.UserRepository;

import jakarta.persistence.PersistenceException;

@Service
public class UserService {
	
	private UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	
	public boolean adduser(String email, String password, String nickname) {
		
		String encodedPassword = HashEncoder.encoder(password);
		
		User user = User.builder().email(email).nickname(nickname)
				.password(encodedPassword).build();
		
		try {
			userRepository.save(user);
		} catch(PersistenceException e) {
			return false;
		}
		return true; 
	}
	
	public boolean emailCheck(String email) {
		Optional<User> opUser = userRepository.findByEmail(email);
		User user = opUser.orElse(null);
		if(user == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean nicknameCheck(String nickname) {
		Optional<User> opUser = userRepository.findByNickname(nickname);
		User user = opUser.orElse(null);
		if(user == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public User login(String email, String password) {
		
		Optional<User> opuser = userRepository.findByEmail(email);
		// 일단 이메일로 유저 찾아보고, 그다음 비밀번호 비교 해야하는데, 
		if(opuser.isPresent()) {
			User user = opuser.get();
			String encodedPassword = HashEncoder.encoder(password);
			if(user.getPassword().equals(encodedPassword)) {
				return user;
			} else {
				return null;
			}  // 조건문의 else 처리가 미흡했나.?
		}
		
		return null;
	}
	
	public User getUserById(long id) {
		Optional<User> opuser =  userRepository.findById(id);
		User user = opuser.orElse(null);
		
		return user;
	}
	

}
