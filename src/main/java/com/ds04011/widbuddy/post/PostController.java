package com.ds04011.widbuddy.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ds04011.widbuddy.category.domain.Category;
import com.ds04011.widbuddy.category.dto.CategoryDto;
import com.ds04011.widbuddy.category.dto.CategoryDtoAssembler;
import com.ds04011.widbuddy.category.service.CategoryService;
import com.ds04011.widbuddy.comment.dto.CommentDto;
import com.ds04011.widbuddy.comment.service.CommentService;
import com.ds04011.widbuddy.hashtag.domain.Hashtag;
import com.ds04011.widbuddy.hashtag.service.HashtagService;
import com.ds04011.widbuddy.joinflag.Service.JoinflagService;
import com.ds04011.widbuddy.joinrequest.dto.JoinrequestDto;
import com.ds04011.widbuddy.joinrequest.service.JoinrequestService;
import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.dto.PostDto;
import com.ds04011.widbuddy.post.dto.PostDtoAssembler;
import com.ds04011.widbuddy.post.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/post/view")
@RequiredArgsConstructor
public class PostController {
	
	private final JoinflagService joinflagService;
	private final JoinrequestService joinrequestService;
	private final CategoryDtoAssembler categoryDtoAssembler;
	private final PostDtoAssembler postDtoAssembler;
	private final PostService postService;
	private final CommentService commentService;
	private final CategoryService categoryService;
	private final HashtagService hashtagService;
	
	
	@GetMapping("/mainpage")
	public String mainpage(Model model) {
		
		
		List<Post> postList = postService.getAllPost();
		List<PostDto> postDtoList = postDtoAssembler.toDtoList(postList);
		
		List<Category> categoryList  = categoryService.getAllCategories();
		List<CategoryDto> categoryDtoList =   categoryDtoAssembler.toDtoList(categoryList);
		
		
		model.addAttribute("postList", postDtoList);
		model.addAttribute("categoryList", categoryDtoList);
		// sjpoint 
		return "post/mainpage";  
	}
		
	
	@GetMapping("/allpost")
	public String allpostpage(Model model) {
		
		List<Post> postList = postService.getAllPost();
		List<PostDto> postDtoList = postDtoAssembler.toDtoList(postList);
		model.addAttribute("postList", postDtoList);
		
		
		return "post/allpost";
	}
	
	@GetMapping("/create")
	public String createPost(@RequestParam("categoryId") long categoryId
			, Model model) {
		
		model.addAttribute("categoryId", categoryId);
		return "post/input";
	}
	
	@GetMapping("/detail")
	public String postDetail(@RequestParam("postId") long postId
			, Model model
			) {
		
		
		
		Post post = postService.getPostById(postId);
		PostDto pd = postDtoAssembler.toDto(post);
		model.addAttribute("post", pd);
		
		List<CommentDto> cdList = commentService.getCommentByPostId(postId);
		model.addAttribute("comments", cdList);
		
		
		//joinflag Id 를 여기서 실어주자? dto 로 하는게 맞다? 컨트롤러로 받아서 서비스에서 처리하는 메서드가 맞다?
		long joinflagId = joinflagService.callJoinflagId(postId);
		model.addAttribute("joinflagId", joinflagId);
		
		// 여기서 해당 flag 에 대응하는 request 들 dto 로 짜서 보내줘
		// 그럼 그걸 모달에서 쭉 띄워줄꺼야. 
		List<JoinrequestDto> jdList = joinrequestService.findByJoinflagId(joinflagId);
		model.addAttribute("requestList", jdList);
		
		List<Hashtag> hashtagList = hashtagService.findByPostId(postId);
		model.addAttribute("hashtags", hashtagList);

		
		return "post/postdetail";
		
		
	}
	
}
