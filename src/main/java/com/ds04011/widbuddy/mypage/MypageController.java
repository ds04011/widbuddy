package com.ds04011.widbuddy.mypage;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.category.dto.CategoryDto;
import com.ds04011.widbuddy.category.dto.CategoryDtoAssembler;
import com.ds04011.widbuddy.category.service.CategoryService;
import com.ds04011.widbuddy.interest.service.InterestService;
import com.ds04011.widbuddy.joinflag.Service.JoinflagService;
import com.ds04011.widbuddy.joinflag.domain.Joinflag;
import com.ds04011.widbuddy.joinrequest.domain.Joinrequest;
import com.ds04011.widbuddy.joinrequest.service.JoinrequestService;
import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.dto.PostDto;
import com.ds04011.widbuddy.post.dto.PostDtoAssembler;
import com.ds04011.widbuddy.post.service.PostService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {
	
	private final InterestService interestService;
	private final CategoryService categoryService; // 필요한가? ㅇㅇ 관심 태그 달린거 가져와야
	private final CategoryDtoAssembler categoryDtoAssembler;
	private final JoinrequestService joinrequestService; 
	private final JoinflagService joinflagService;
	private final PostDtoAssembler postDtoAssembler;
	private final PostService postService;
	
	
	
	// mypage 관련 호출기능 모으기.
	@GetMapping("/user/view/mypage")
	public String mypage(HttpSession session
			, Model model) {
		
		long userId = (Long) session.getAttribute("userId");
		
		//  hashtag 모두가 담겨서 보내져야 함. 
		List<Post> postList = postService.getPostsByUserId(userId);
		List<PostDto> postDtoList = postDtoAssembler.toDtoList(postList);
		model.addAttribute("postList", postDtoList);
		
		List<Category> cateList = interestService.cateListByUserId(userId);
		List<CategoryDto> cateDtoList = categoryDtoAssembler.toCateDtoList(cateList, userId);
		model.addAttribute("categoryList", cateDtoList);
		
		
		List<Joinrequest> requestList = joinrequestService.findByUserId(userId);
		model.addAttribute("requestList", requestList);
		List<Joinflag> flagList =  joinflagService.findByUserId(userId);
		model.addAttribute("flagList", flagList);
		
		
		
		return "mypage/mypage";
	}

}
