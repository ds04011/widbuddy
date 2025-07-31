package com.ds04011.widbuddy.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.category.service.CategoryService;
import com.ds04011.widbuddy.post.PostDtoAssembler;
import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.dto.PostDto;
import com.ds04011.widbuddy.post.repository.PostRepository;
import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.service.UserService;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {
	

	private PostRepository postRepository;
	private PostDtoAssembler postDtoAssembler;
	
	public PostService(PostRepository postRepository
			, PostDtoAssembler postDtoAssembler
			) {
		this.postRepository = postRepository;
		this.postDtoAssembler = postDtoAssembler;
	}
	
	public boolean addPost(long userId, String title, String contents, long categoryId, String imagePath) {
								// imagePath 추후 추가
		
		Post post = Post.builder().userId(userId).title(title)
				.contents(contents)
				.categoryId(categoryId)
				.imagePath(imagePath)
				.build();
		// imagePath 추후 추가
		
		try {
			postRepository.save(post);
			
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
	}
	
	public List<Post> getAllPost(){
		return postRepository.findAll();
	}
	
	public List<Post> getPostByCategoryId(long categoryId){
		List<Post> postList =  postRepository.findAllByCategoryId(categoryId);
		
		return postList;
	}
	
	public List<PostDto> getPostDtoByCategoryId(long categoryId){
		List<Post> postList =  postRepository.findAllByCategoryId(categoryId);
		List<PostDto> postDtoList = postDtoAssembler.toDtoList(postList);
		
		return postDtoList;
	}
	
	public List<PostDto> getAllPostDto(){
		List<Post> postList = postRepository.findAll();
	    return postDtoAssembler.toDtoList(postList);

	}
	
	public PostDto getPostDtoById(long id) {
		Optional<Post> oppost = postRepository.findById(id);
		Post p = oppost.orElse(null);
		return postDtoAssembler.toDto(p);
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

}
