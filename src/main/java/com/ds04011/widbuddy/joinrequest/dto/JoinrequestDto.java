package com.ds04011.widbuddy.joinrequest.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ds04011.widbuddy.post.dto.PostDto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder=true)
public class JoinrequestDto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private long joinFlagId;
	private long userId;
	private String description;
	private String state;
	private String userName;

	private LocalDateTime createdAt;


}
