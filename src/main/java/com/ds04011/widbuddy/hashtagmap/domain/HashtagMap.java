package com.ds04011.widbuddy.hashtagmap.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.ds04011.widbuddy.hashtag.domain.Hashtag;

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
@Table(name="`hashtagmap`")
@Getter
@Setter
@Builder(toBuilder=true)
@AllArgsConstructor                              
@NoArgsConstructor
public class HashtagMap {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private long postId;
	private long hashtagId;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	
}
