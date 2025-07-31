package com.ds04011.widbuddy.category.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder=true)
@Getter
public class CategoryDto {
	
	private long id;  		//카테고리 id 
	private String name; 	// 카테고리 이름 
	private String description; // 카테고리 설명 
	
	private String nickname; // 생성한 유저 닉네임
	private int postNumber;
	
	
	private LocalDateTime createdAt;
	
	

}
