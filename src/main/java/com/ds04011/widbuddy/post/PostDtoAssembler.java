package com.ds04011.widbuddy.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.category.service.CategoryService;
import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.dto.PostDto;
import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.service.UserService;

@Component
public class PostDtoAssembler {
	
	private final UserService userService;
    private final CategoryService categoryService;

    public PostDtoAssembler(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

	
	 public List<PostDto> toDtoList(List<Post> postList) {
	        List<PostDto> dtoList = new ArrayList<>();
	        for (Post p : postList) {
	            String categoryName = categoryService.getCategoryById(p.getCategoryId()).getName();
	            dtoList.add(PostDto.builder()
	                .id(p.getId())
	                .userId(p.getUserId())
	                .title(p.getTitle())
	                .contents(p.getContents())
	                .imagePath(p.getImagePath())
	                .createdAt(p.getCreatedAt())
	                .categoryId(p.getCategoryId())
	                .categoryName(categoryName)
	                .build());
	        }
	        return dtoList;
	    }
	 
	 public PostDto toDto(Post post) {
	        User user = userService.getUserById(post.getUserId());
	        Category category = categoryService.getCategoryById(post.getCategoryId());

	        return PostDto.builder()
	            .id(post.getId())
	            .nickname(user.getNickname())
	            .title(post.getTitle())
	            .contents(post.getContents())
	            .imagePath(post.getImagePath())
	            .createdAt(post.getCreatedAt())
	            .categoryName(category.getName())
	            .categoryId(category.getId())
	            .build();
	    }	
	
}
