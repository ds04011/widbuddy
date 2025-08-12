package com.ds04011.widbuddy.hashtag;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ds04011.widbuddy.hashtag.domain.Hashtag;
import com.ds04011.widbuddy.hashtag.repository.HashtagRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HashtagController {
	
	
	private final HashtagRepository hashtagRepository;
	
	@GetMapping("/hashtag/view")
	public String hashtags(Model model) {
		
		
		List<Hashtag> hashtagList = hashtagRepository.findAll();
		model.addAttribute("hashtags", hashtagList);
		
		return "hashtag/hashtag";
	}

}
