package com.ds04011.widbuddy.category.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.post.repository.PostRepository;
import com.ds04011.widbuddy.post.service.PostService;
import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.service.UserService;

@Component
public class CategoryDtoAssembler {
	
	private final PostService postService;
    private final UserService userService;
   

    public CategoryDtoAssembler(UserService userService
    		, PostService postService) {
        this.postService = postService;
        this.userService = userService;
    }
    
    public List<CategoryDto> toDtoList(List<Category> categoryList) {
        List<CategoryDto> dtoList = new ArrayList<>();
        for (Category c : categoryList) {
            User user = userService.getUserById(c.getUserId());
            int postCount = postService.countPostNumber(c.getId());   
            dtoList.add(CategoryDto.builder()
                .name(c.getName())
                .description(c.getDescription())
                .createdAt(c.getCreatedAt())
                .id(c.getId())
                .nickname(user.getNickname())
                .postNumber(postCount)
                .build());
        }
        return dtoList;
    }
    // 저게맞는지? repository 를 받아와서 써도 되나? 당연히 안되지!
	
}
