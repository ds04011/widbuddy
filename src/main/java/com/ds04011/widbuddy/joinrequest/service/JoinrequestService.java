package com.ds04011.widbuddy.joinrequest.service;

import java.util.List;

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
				.state("대기중")
				.build();
		// state 는 기본이 대기중 이것도 빌더에 넣어줘야 함. 
		
		try {
			joinrequestRepository.save(join);
		} catch(PersistenceException e) {
			return false;
		}
		return true;	
	}
	
	public List<Joinrequest> findByUserId(long userId){
		List<Joinrequest> requestList =  joinrequestRepository.findByUserId(userId);
		return requestList;
	}
	
	
}
