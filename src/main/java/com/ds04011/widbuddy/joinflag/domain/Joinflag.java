package com.ds04011.widbuddy.joinflag.domain;

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

@Entity
@Table(name="`joinflag`")
@Getter
@Setter
@Builder(toBuilder=true)
@AllArgsConstructor                              
@NoArgsConstructor 
public class Joinflag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private long userId;
	private long postId;
	private int headcount;
	private int currentNumber = 0;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
}
