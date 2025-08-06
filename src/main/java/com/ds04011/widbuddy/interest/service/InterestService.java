package com.ds04011.widbuddy.interest.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.interest.domain.Interest;
import com.ds04011.widbuddy.interest.repository.InterestRepository;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterestService {

	private final InterestRepository interestRepository;
	
	public boolean addInterest(long userId, long categoryId) {
		
		Interest interest = Interest.builder()
				.userId(userId)
				.categoryId(categoryId)
				.build();
		
		try {
			interestRepository.save(interest);
		} catch (PersistenceException e) {
			return false;
		}
		return true;
		
	}
	
	public boolean deleteInterest(long userId, long categoryId) {
		Optional<Interest> opter = interestRepository.findByUserIdAndCategoryId(userId, categoryId);
		Interest inter = opter.orElse(null);
		if(inter !=null) {
			interestRepository.delete(inter);
			return true;
		} else {
			return false;
		}
	}
	
	public int isInterest(long userId, long categoryId) {
		Optional<Interest> opter = interestRepository.findByUserIdAndCategoryId(userId, categoryId);
		Interest inter = opter.orElse(null);
		if(inter != null) {
			return 1;  // 관심등록함.
		} else {
			return 0;  // 관심등록 안함
		}
	} // 지금 이건, 컬럼 값 안쓰고 존재여부로 확인하는건데, 애매하네, 일단 지우자. 
	
	public int countByCategoryId(long categoryId) {
		return interestRepository.countByCategoryId(categoryId);
	}
	
}
