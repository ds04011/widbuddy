package com.ds04011.widbuddy.comment.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name="`comment`")
@Builder(toBuilder=true) 
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private long userId;
	private long postId;
	
	private String contents;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
}
