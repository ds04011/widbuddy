package com.ds04011.widbuddy.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.joinflag.Service.JoinflagService;
import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.repository.PostRepository;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {
	

	private PostRepository postRepository;
	private JoinflagService joinflagService;
	
	public PostService(PostRepository postRepository
			, JoinflagService joinflagService
			) {
		this.postRepository = postRepository;
		this.joinflagService = joinflagService;
		
	}
	
	public boolean addPost(long userId, String title, String contents, long categoryId, String imagePath
			, int headcount) {
								
		// 1. post 생성.
		Post post = Post.builder().userId(userId).title(title)
				.contents(contents)
				.categoryId(categoryId)
				.imagePath(imagePath)
				.build();
		
		
		try {
			Post genPost = postRepository.save(post);
		
			
			if(headcount !=0) {
				joinflagService.addJoinflag(userId, genPost, headcount);
			}
			
			
		} catch(PersistenceException e) {
			return false;
		}
		return true;
	}
	
	public Post getPostById(long id) {
		Optional<Post> oppost = postRepository.findById(id);
		Post post = oppost.orElse(null);
		return post;
	}
	
	public List<Post> getAllPost(){
		return postRepository.findAll();
	}
	
	public List<Post> getPostByCategoryId(long categoryId){
		List<Post> postList =  postRepository.findAllByCategoryId(categoryId);
		
		return postList;
	}
	

	/*
	 *  public PostDto getPostDtoById(long id) 
	 *  { Post post = postRepository.findById(id) .orElseThrow(() -> new PostNotFoundException(id)); 
	 *  return postDtoAssembler.toDto(post); 
	 *  }
				예외처리 클래스 만들어서 이렇게 하는게 null 일 경우 더 안전하긴 하다. 
	 * */
	
	
	public int countPostNumber(long categoryId) {
		int count = postRepository.countByCategoryId(categoryId);
		return count;
	}
	
	public List<Post> getPostsByUserId(long userId){
		return postRepository.findAllByUserId(userId);
	}

}
