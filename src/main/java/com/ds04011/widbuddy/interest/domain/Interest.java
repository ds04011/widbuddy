package com.ds04011.widbuddy.interest.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.ds04011.widbuddy.comment.domain.Comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor                              
@NoArgsConstructor
@Entity
@Table(name="`interest`")
@Builder(toBuilder=true) 
public class Interest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	private long userId;
	private long categoryId;
	
	//private boolean isInterest;  
	// 관심 버튼 표현을 위한, 
	// 이게 조금 정확한 판단이 헷갈린다. DB 로 존재 여부확인으로 하는것과, 컬럼값으로 관심여부를 확인하는것
	// 중에서 어떤게 효율적일까? 애매하다. 일단은 존재 여부로 반영하자. 
	//애초에 필요없네,ㅏ 존재여부가 곧 값을 알려주는거니,
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	
}
