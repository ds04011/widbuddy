package com.ds04011.widbuddy.joinrequest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ds04011.widbuddy.joinflag.Service.JoinflagService;
import com.ds04011.widbuddy.joinrequest.domain.Joinrequest;
import com.ds04011.widbuddy.joinrequest.dto.JoinrequestDto;
import com.ds04011.widbuddy.joinrequest.repository.JoinrequestRepository;
import com.ds04011.widbuddy.post.domain.Post;
import com.ds04011.widbuddy.post.service.PostService;
import com.ds04011.widbuddy.user.domain.User;
import com.ds04011.widbuddy.user.service.UserService;

import jakarta.persistence.PersistenceException;

@Service
public class JoinrequestService {

	private JoinflagService joinflagService;
	private UserService userService;
	private JoinrequestRepository joinrequestRepository;
	public JoinrequestService(JoinrequestRepository joinrequestRepository
			, UserService userService
			, JoinflagService joinflagService) {
		this.joinrequestRepository = joinrequestRepository;
		this.userService = userService;
		this.joinflagService = joinflagService;
	}
	
	public boolean addRequest(long joinflagId, long userId, String description) {
		Joinrequest join = Joinrequest.builder()
				.joinFlagId(joinflagId)
				.userId(userId)
				.description(description)
				.state("대기중")
				.build();
		// state 는 기본이 대기중 이것도 빌더에 넣어줘야 함. 
		
		try {
			joinrequestRepository.save(join);
		} catch(PersistenceException e) {
			return false;
		}
		return true;	
	}
	
	public List<Joinrequest> findByUserId(long userId){
		List<Joinrequest> requestList =  joinrequestRepository.findByUserId(userId);
		return requestList;
	}
	
	public List<JoinrequestDto> findByJoinflagId(long joinflagId){
		List<Joinrequest> requestList =  joinrequestRepository.findByJoinFlagId(joinflagId);
		List<JoinrequestDto> requestDtoList = new ArrayList<>();
		for(Joinrequest r : requestList) {
			if(r.getState().equals("대기중")) {
				
				User user = userService.getUserById(r.getUserId());
				String userName = user.getNickname();
				
				JoinrequestDto jd = JoinrequestDto.builder()
						.id(r.getId())
						.joinFlagId(r.getJoinFlagId())
						.createdAt(r.getCreatedAt())
						.description(r.getDescription())
						.userId(r.getUserId())
						.state(r.getState())
						.userName(userName)
						.build();
				
				requestDtoList.add(jd);
			}
		}
		return requestDtoList;
	}
	
	
	public List<JoinrequestDto> requestDtoList(List<Joinrequest> requestList){
		List<JoinrequestDto> requestDtoList = new ArrayList<>();
		
		for(Joinrequest r : requestList) {
			
				User user = userService.getUserById(r.getUserId());
				String userName = user.getNickname();
				Post post = joinflagService.findPostById(r.getJoinFlagId());
				
				JoinrequestDto jd = JoinrequestDto.builder()
						.id(r.getId())
						.joinFlagId(r.getJoinFlagId())
						.createdAt(r.getCreatedAt())
						.description(r.getDescription())
						.userId(r.getUserId())
						.state(r.getState())
						.userName(userName)
						.postId(post.getId())
						.title(post.getTitle())
						.build();
				
				requestDtoList.add(jd);
			
		}
		return requestDtoList;
	}
	
	public Joinrequest findById(long joinrequestId) {
		Optional<Joinrequest> oprequest = joinrequestRepository.findById(joinrequestId);
		Joinrequest request = oprequest.orElse(null);
		return request;
	}
	
	public boolean saveRequest(Joinrequest request) {
		
		try {
			joinrequestRepository.save(request);
		} catch(PersistenceException e) {
			return false;
		}
		return true;	
	}
	
}
