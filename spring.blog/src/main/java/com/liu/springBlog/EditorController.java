package com.liu.springBlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.liu.springBlog.model.BlogInfo;
import com.liu.springBlog.repository.UserInfoRepository;

@Controller
public class EditorController {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private UserInfoRepository blogInfoRepository;
	
	@GetMapping("/blog")
	public String getBlogView() {
		
		return "Lblog";
	}
	
	@PostMapping("/editor")
	public ModelAndView editor(
		@RequestParam("title")String title,
		@RequestParam("introduction")String introduction,
		@RequestParam("contents")String contents,
		ModelAndView mv) {
		
		BlogInfo blogInfo = BlogInfo.builder()
				.title(title)//
				.introduction(introduction)//
				.contents(contents)//
				.build();
		
		blogInfoRepository.save(blogInfo);
		
		mv.setViewName("Lblog");
		
		return mv;
		
	}
	
}
