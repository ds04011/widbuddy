package com.ds04011.widbuddy.joinrequest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.widbuddy.joinrequest.domain.Joinrequest;

public interface JoinrequestRepository extends JpaRepository<Joinrequest, Long>{

	public List<Joinrequest> findByUserId(long userId);
	
	public List<Joinrequest> findByJoinFlagId(long joinflagId);
	
}
