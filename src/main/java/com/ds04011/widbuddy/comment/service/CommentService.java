package com.ds04011.widbuddy.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.comment.domain.Comment;
import com.ds04011.widbuddy.comment.dto.CommentDto;
import com.ds04011.widbuddy.comment.repository.CommentRepository;
import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.service.UserService;

import jakarta.persistence.PersistenceException;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	private UserService userService;
	public CommentService(CommentRepository commentRepository
			, UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
	}
	
	public boolean addComment(long userId, long postId, String contents) {
		
		Comment comment = Comment.builder()
				.userId(userId)
				.postId(postId)
				.contents(contents)
				.build();
		
		try {
			commentRepository.save(comment);
		} catch(PersistenceException e) {
			return false;
		}
		return true;
	}
	
	public List<CommentDto> getCommentByPostId(long postId) {
		
		List<Comment> commentList = commentRepository.findAllByPostId(postId);
		List<CommentDto> cdList = new ArrayList<>();
		
		for(Comment c : commentList) {
			User user = userService.getUserById(c.getUserId()); 
			
			CommentDto cd = CommentDto.builder()
					.postId(c.getPostId())
					.userId(c.getUserId())
					.contents(c.getContents())
					.createdAt(c.getCreatedAt())
					.nickname(user.getNickname())
					.build();
			cdList.add(cd);
		}
		return cdList;
	}
	
}
