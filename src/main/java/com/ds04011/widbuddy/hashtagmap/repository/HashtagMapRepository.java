package com.ds04011.widbuddy.hashtagmap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.widbuddy.hashtagmap.domain.HashtagMap;

public interface HashtagMapRepository extends JpaRepository<HashtagMap, Long>{
	
	public List<HashtagMap> findByPostId(long postId);
}
