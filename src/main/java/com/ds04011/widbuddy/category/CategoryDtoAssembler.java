package com.ds04011.widbuddy.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.category.dto.CategoryDto;
import com.ds04011.widbuddy.post.repository.PostRepository;
import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.service.UserService;

@Component
public class CategoryDtoAssembler {
	
	//private final PostService postService;
    private final UserService userService;
    private final PostRepository postRepository;

    public CategoryDtoAssembler(UserService userService
    		, PostRepository postRepository) {
//        this.postService = postService;
        this.userService = userService;
        this.postRepository = postRepository;
    }
    
    public List<CategoryDto> toDtoList(List<Category> categoryList) {
        List<CategoryDto> dtoList = new ArrayList<>();
        for (Category c : categoryList) {
            User user = userService.getUserById(c.getUserId());
            int postCount = postRepository.countByCategoryId(c.getId());
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

	
}
