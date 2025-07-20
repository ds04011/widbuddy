package com.ds04011.widbuddy.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.widbuddy.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	

	public Optional<User> findByEmail(String email);
	public Optional<User> findByNickname(String nickname);
//	public Optional<User> findByEmailAndPassword(String email, String password);
}
