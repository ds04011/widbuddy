package com.ds04011.widbuddy.category.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.interest.service.InterestService;
import com.ds04011.widbuddy.post.service.PostService;
import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.service.UserService;

@Component
public class CategoryDtoAssembler {
	
	private final PostService postService;
    private final UserService userService;
    private final InterestService interestService;

    public CategoryDtoAssembler(UserService userService
    		, PostService postService
    		, InterestService interestService) {
        this.postService = postService;
        this.userService = userService;
        this.interestService = interestService;
    }
    
    public List<CategoryDto> toDtoList(List<Category> categoryList) {
        List<CategoryDto> dtoList = new ArrayList<>();
        for (Category c : categoryList) {
            User user = userService.getUserById(c.getUserId());
            int postCount = postService.countPostNumber(c.getId());   
            int interestNumber = interestService.countByCategoryId(c.getId());
            
            dtoList.add(CategoryDto.builder()
                .name(c.getName())
                .description(c.getDescription())
                .createdAt(c.getCreatedAt())
                .id(c.getId())
                .nickname(user.getNickname())
                .postNumber(postCount)
                .interestNumber(interestNumber)
                .build());
        }
        return dtoList;
    }
    
    public List<CategoryDto> toCateDtoList(List<Category> categoryList, long userId) {
        List<CategoryDto> dtoList = new ArrayList<>();
        for (Category c : categoryList) {
            User user = userService.getUserById(c.getUserId());
            int postCount = postService.countPostNumber(c.getId());  
            int isInterest = interestService.isInterest(userId, c.getId());
            
            dtoList.add(CategoryDto.builder()
                .name(c.getName())
                .description(c.getDescription())
                .createdAt(c.getCreatedAt())
                .id(c.getId())
                .nickname(user.getNickname())
                .postNumber(postCount)
                .isInterest(isInterest)
                .build());
            
            // isInterest 를 추가해서 얻어와야 하고, 
            // 세션에서 userId 받아와서, 그걸로 interestService 의 메서드를 호출 할 수 있겠다. 
            // 그걸로 각 유저 별 categoryDto 를 생성할 수 있고, 그걸로 각 버튼의 형태를 정하자. 
            
            
            //해당 카테고리의, 게시글 중, joinflag 들, 모두 호출해서, headcount - currentNumber 값을 누적해서 더해서 
            // 카테고리Dto 의 totalrecruitNumber 에 저장, 해서 dto 생성, 
        }
        return dtoList;
    }
    
    
	
}
