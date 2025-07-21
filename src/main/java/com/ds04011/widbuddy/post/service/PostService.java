package com.ds04011.widbuddy.post.service;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.repository.PostRepository;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {
	
	private PostRepository postRepository;
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public boolean addPost(long userId, String title, String contents, long categoryId) {
								// imageFile 추후 추가
		
		Post post = Post.builder().userId(userId).title(title)
				.contents(contents)
				.categoryId(categoryId)
				.build();
		// imageFile 추후 추가
		
		try {
			postRepository.save(post);
			
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
	}

}
