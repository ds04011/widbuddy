package com.ds04011.widbuddy.post.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.category.service.CategoryService;
import com.ds04011.widbuddy.joinflag.Service.JoinflagService;
import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.service.UserService;

@Component
public class PostDtoAssembler {
	
	private final UserService userService;
    private final CategoryService categoryService;
    private final JoinflagService joinflagService; 

    public PostDtoAssembler(UserService userService, CategoryService categoryService
    		, JoinflagService joinflagService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.joinflagService = joinflagService;
    }

	// post service 를 주입받아서, 리스트 여기서 생성하고 dto 여기서 생성하는 형태로, 
	 public List<PostDto> toDtoList(List<Post> postList) {
	        List<PostDto> dtoList = new ArrayList<>();
	        for (Post p : postList) {
	            String categoryName = categoryService.getCategoryById(p.getCategoryId()).getName();
	            int headcount = joinflagService.callHeadcount(p.getId()); 
		        int currentNumber = joinflagService.callCurrentNumber(p.getId());
		        
	            dtoList.add(PostDto.builder()
	                .id(p.getId())
	                .userId(p.getUserId())
	                .title(p.getTitle())
	                .contents(p.getContents())
	                .imagePath(p.getImagePath())
	                .createdAt(p.getCreatedAt())
	                .categoryId(p.getCategoryId())
	                .categoryName(categoryName)
	                .headcount(headcount)
		            .currentNumber(currentNumber)
	                .build());
	            
	            
	            
	        }
	        return dtoList;
	    }
	 
	 public PostDto toDto(Post post) {
	        User user = userService.getUserById(post.getUserId());
	        Category category = categoryService.getCategoryById(post.getCategoryId());
	        int headcount = joinflagService.callHeadcount(post.getId()); 
	        int currentNumber = joinflagService.callCurrentNumber(post.getId());
	        return PostDto.builder()
	            .id(post.getId())
	            .nickname(user.getNickname())
	            .title(post.getTitle())
	            .contents(post.getContents())
	            .imagePath(post.getImagePath())
	            .createdAt(post.getCreatedAt())
	            .categoryName(category.getName())
	            .categoryId(category.getId())
	            .headcount(headcount)
	            .currentNumber(currentNumber)
	            .build();
	        
	        // postDto 에 headcount 를 넣어주려고 하는데, 
	        // 우선 default 로 0 으로 할당되어있고, 
	        // joinflag 에서 postId 로 해당 게시글의 joinflag 를 조회하고, 있으면 headcount 의 값을 가져오고, 
	        // 없으면 0 으로 할당해서 보내주자 , 
	        // view 에서 0 이면 , 안보여주고, 다른 값이 존재하면 보여주기, 
	    }	
	
}
