package com.ds04011.widbuddy.comment.dto;

import java.time.LocalDateTime;



import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder=true)
public class CommentDto {
	
	private long Id;
	
	private String contents;
	private long userId;
	private long postId;
	
	private String nickname;
	private LocalDateTime createdAt;
	
}
