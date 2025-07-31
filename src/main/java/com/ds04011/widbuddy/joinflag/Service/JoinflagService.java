package com.ds04011.widbuddy.joinflag.Service;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.joinflag.domain.Joinflag;
import com.ds04011.widbuddy.joinflag.repository.JoinflagRepository;

import jakarta.persistence.PersistenceException;

@Service
public class JoinflagService {
	
	private JoinflagRepository joinflagRepository;
	public JoinflagService(JoinflagRepository joinflagRepository) {
		this.joinflagRepository = joinflagRepository;
	}
	
	public boolean addJoinflag(long userId, long postId, int headcount) {
		
		Joinflag flag = Joinflag.builder()
				.userId(userId)
				.postId(postId)
				.headcount(headcount)
				.build();
		try {
			joinflagRepository.save(flag);
		} catch (PersistenceException e) {
			return false;
		}
		return true;
	}

}
