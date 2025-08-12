package com.ds04011.widbuddy.hashtag.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.hashtag.domain.Hashtag;
import com.ds04011.widbuddy.hashtag.repository.HashtagRepository;
import com.ds04011.widbuddy.hashtagmap.domain.HashtagMap;
import com.ds04011.widbuddy.hashtagmap.repository.HashtagMapRepository;

	

@Service
public class HashtagService {
	
	private HashtagRepository hashtagRepository;
	private HashtagMapRepository hashtagMapRepository;
	public HashtagService(HashtagRepository hashtagRepository
			, HashtagMapRepository hashtagMapRepository) {
		this.hashtagRepository = hashtagRepository;
		this.hashtagMapRepository = hashtagMapRepository;
	}
	
	public List<Hashtag> findByPostId(Long postId) {
	    
		List<HashtagMap> maps = hashtagMapRepository.findByPostId(postId);
		List<Long> hashtagIds = maps.stream()
                .map(HashtagMap::getHashtagId)
                .collect(Collectors.toList());
		if (hashtagIds.isEmpty()) {
		    return Collections.emptyList();
		}

		
		List<Hashtag> hashtags = hashtagRepository.findByIdIn(hashtagIds);
		
		return hashtags;

	}


}
