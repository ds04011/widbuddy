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
            
            // isInterest 를 추가해서 얻어와야 하고, interest 서비스에서 , 메서드 하나 더 만들어서 세션정보로 
            // userId 받아와서, 그걸로 interestService 의 메서드를 호출 할 수 있겠다. 
            // 그걸로 각 유저 별 categoryDto 를 생성할 수 있고, 그걸로 각 버튼의 형태를 정하자. 
        }
        return dtoList;
    }
    
	
}
