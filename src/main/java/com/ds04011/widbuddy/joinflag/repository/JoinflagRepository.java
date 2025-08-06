package com.ds04011.widbuddy.joinflag.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.widbuddy.joinflag.domain.Joinflag;

public interface JoinflagRepository extends JpaRepository<Joinflag,Long>{
	
	public Optional<Joinflag> findByPostId(long postId);
	public List<Joinflag> findByUserId(long userId);

}
