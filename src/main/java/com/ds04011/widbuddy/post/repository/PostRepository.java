package com.ds04011.widbuddy.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds04011.widbuddy.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	

}
