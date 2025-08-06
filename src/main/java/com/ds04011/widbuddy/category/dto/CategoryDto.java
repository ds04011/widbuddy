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
	
	private int isInterest;
	private int interestNumber;
	
	// 모집중인 인원 수를 알려야해, 기본적으로 joinflag 의 정보가 필요하다. 
	// joinflag 의 각 객체 마다, headcount - currentNumber 의 값을, 누적해서 더해, 
	private int totalRecruitNumber;
	
	private LocalDateTime createdAt;
	
	

}
