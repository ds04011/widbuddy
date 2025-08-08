package com.ds04011.widbuddy.joinflag.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.joinflag.domain.Joinflag;
import com.ds04011.widbuddy.joinflag.repository.JoinflagRepository;
import com.ds04011.widbuddy.joinrequest.domain.Joinrequest;
import com.ds04011.widbuddy.post.domain.Post;

import jakarta.persistence.PersistenceException;

@Service
public class JoinflagService {
	
	private JoinflagRepository joinflagRepository;
	public JoinflagService(JoinflagRepository joinflagRepository) {
		this.joinflagRepository = joinflagRepository;
	}
	
	public boolean addJoinflag(long userId, Post post, int headcount) {
		
		Joinflag flag = Joinflag.builder()
				.userId(userId)
				.post(post)
				.headcount(headcount)
				.build();
		try {
			joinflagRepository.save(flag);
		} catch (PersistenceException e) {
			return false;
		}
		return true;
	}
	
	public Joinflag findById(long id) {
		Optional<Joinflag> opflag = joinflagRepository.findById(id);
		Joinflag flag = opflag.orElse(null);
		return flag;
	}
	
	public int callHeadcount(long postId) {
		
		
		// postId 로 flag  존재 여부 파악, 있으면 해당 flag 의 headcount 리턴, 없으면 0 리턴 
		
		Optional<Joinflag> opflag = joinflagRepository.findByPostId(postId);
		Joinflag flag = opflag.orElse(null);
		
		if(flag == null) {
			return 0;
		} else {
			return flag.getHeadcount();
		}
	}
	
	public int callCurrentNumber(long postId) {
		Optional<Joinflag> opflag = joinflagRepository.findByPostId(postId);
		Joinflag flag = opflag.orElse(null);
		
		if(flag == null) {
			return 0;
		} else {
			return flag.getCurrentNumber();
		}
	}
	
	public long callJoinflagId(long postId) {
		Optional<Joinflag> opflag = joinflagRepository.findByPostId(postId);
		Joinflag flag = opflag.orElse(null);
		
		if(flag == null) {
			return 0;  // 게시글마다 있는게 아니라서, 없는 경우, 0 리턴 
		} else {
			return flag.getId();  // 있으면 값 리턴 
		}
	}
	
	public List<Joinflag> findByUserId(long userId){
		List<Joinflag> flagList = joinflagRepository.findByUserId(userId);
		return flagList;
	}
	
	public Post findPostById(long joinflagId) {
		Optional<Joinflag> opflag = joinflagRepository.findById(joinflagId);
		Joinflag flag = opflag.orElse(null);
		if(flag == null) {
			return null;
		} else {
			Post post = flag.getPost();
			return post;
		}
	}
	
	public boolean saveFlag(Joinflag flag) {
			
			try {
				joinflagRepository.save(flag);
			} catch(PersistenceException e) {
				return false;
			}
			return true;	
		}

}
