package com.ds04011.widbuddy.hashtag.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.ds04011.widbuddy.post.domain.Post;

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
@Table(name="`hashtag`")
@Getter
@Setter
@Builder(toBuilder=true)
@AllArgsConstructor                              
@NoArgsConstructor
public class Hashtag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String tag;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	 public Hashtag(String tag) {
	        this.tag = tag;
	    }

	

}
