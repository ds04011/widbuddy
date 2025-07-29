package com.ds04011.widbuddy.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.category.service.CategoryService;
import com.ds04011.widbuddy.common.HtmlUtil;
import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.dto.PostDto;
import com.ds04011.widbuddy.post.repository.PostRepository;
import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.service.UserService;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {
	
	private UserService userService;
	private PostRepository postRepository;
	private CategoryService categoryService;
	public PostService(PostRepository postRepository
			, CategoryService categoryService
			, UserService userService) {
		this.postRepository = postRepository;
		this.categoryService = categoryService;
		this.userService = userService;
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
		List<PostDto> postDtoList = new ArrayList<>();
		Category category = categoryService.getCategoryById(categoryId);
		
		
		for(Post p : postList) {
			PostDto pd = PostDto.builder()
					.id(p.getId())
					.userId(p.getUserId())
					.title(p.getTitle())
					.contents(p.getContents())
					.imagePath(p.getImagePath())
					.createdAt(p.getCreatedAt())
					.categoryId(p.getCategoryId())
					.categoryName(category.getName())
					.build();
			
			postDtoList.add(pd);
			
		}
		
		return postDtoList;
	}
	
	public List<PostDto> getAllPostDto(){
		List<Post> postList  = postRepository.findAll();
		List<PostDto> postDtoList = new ArrayList<>();
		
		for(Post p : postList) {
			
			long userId = p.getUserId();
			User user = userService.getUserById(userId);
			long categoryId = p.getCategoryId();
			Category category = categoryService.getCategoryById(categoryId);
			
			PostDto pd = PostDto.builder()
					.id(p.getId())
					.nickname(user.getNickname())
					.title(p.getTitle())
					.createdAt(p.getCreatedAt())
					.categoryName(category.getName())
					.build();
			
			postDtoList.add(pd);
		}
		return postDtoList;
	}
	
	public PostDto getPostDtoById(long id) {
		Optional<Post> oppost = postRepository.findById(id);
		Post p = oppost.orElse(null);
		long userId = p.getUserId();
		User user = userService.getUserById(userId);
		long categoryId = p.getCategoryId();
		Category category = categoryService.getCategoryById(categoryId);
		
		// contents 를 다듬어서 사용해야함, 태그가 포함되어있어서
//		String contents =  HtmlUtil.extractPlainText(p.getContents());  
		
		PostDto pd = PostDto.builder()
				.id(p.getId())
				.nickname(user.getNickname())
				.title(p.getTitle())
				.contents(p.getContents())
				.imagePath(p.getImagePath())
				.createdAt(p.getCreatedAt())
				.categoryName(category.getName())
				.categoryId(categoryId)
				.build();
		
		return pd;
	}

}
