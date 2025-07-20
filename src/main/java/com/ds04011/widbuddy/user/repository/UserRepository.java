package com.ds04011.widbuddy.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.widbuddy.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	

}
