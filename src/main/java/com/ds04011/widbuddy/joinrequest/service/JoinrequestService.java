package com.ds04011.widbuddy.joinrequest.service;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.joinrequest.domain.Joinrequest;
import com.ds04011.widbuddy.joinrequest.repository.JoinrequestRepository;

import jakarta.persistence.PersistenceException;

@Service
public class JoinrequestService {

	private JoinrequestRepository joinrequestRepository;
	public JoinrequestService(JoinrequestRepository joinrequestRepository) {
		this.joinrequestRepository = joinrequestRepository;
	}
	
	public boolean addRequest(long joinflagId, long userId, String description) {
		Joinrequest join = Joinrequest.builder()
				.joinFlagId(joinflagId)
				.userId(userId)
				.description(description)
				.build();
		// state 는 기본이 대기중 
		
		try {
			joinrequestRepository.save(join);
		} catch(PersistenceException e) {
			return false;
		}
		return true;
		
				
	}
	
	
}
