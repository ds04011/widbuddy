package com.ds04011.widbuddy.hashtag.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ds04011.widbuddy.hashtag.domain.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Long>{

	public Optional<Hashtag> findByTag(String tag);
	
	public List<Hashtag> findByIdIn(List<Long> ids);


}
