package com.ds04011.widbuddy.post.dto;

import java.time.LocalDateTime;

import com.ds04011.widbuddy.post.domain.Post;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder=true)
public class PostDto {
	
	private long id;
	
	private long userId;
	private String userName;
	
	private String title;
	private String contents;
	private String imagePath;
	
	
	private LocalDateTime createdAt;
	
	private String categoryName; 
	private long categoryId;
	private String nickname;

	private int headcount = 0;
	private int currentNumber = 0;
}
