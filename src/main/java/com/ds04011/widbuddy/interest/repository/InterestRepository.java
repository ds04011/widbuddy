package com.ds04011.widbuddy.interest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.widbuddy.interest.domain.Interest;

public interface InterestRepository extends JpaRepository<Interest, Long>{

	public Optional<Interest> findByUserIdAndCategoryId(long userId, long categoryId);
	public int countByCategoryId(long categoryId);
	public List<Interest> findByUserId(long userId);
}
