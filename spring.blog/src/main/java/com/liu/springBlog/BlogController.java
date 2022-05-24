package com.liu.springBlog;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.liu.springBlog.model.BlogInfo;
import com.liu.springBlog.model.UserInfo;
import com.liu.springBlog.repository.BlogInfoRepository;
import com.liu.springBlog.repository.UserInfoRepository;

@Controller
public class BlogController {
	
	@Autowired
	private BlogInfoRepository blogInfoRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	
	@GetMapping("/blog")
	public ModelAndView getBlogView(@RequestParam("username") String username, ModelAndView mv) {
		
		List<BlogInfo> blogs = blogInfoRepository.findAll();
		mv.addObject("blogs", blogs);
		mv.addObject("username", username);
		mv.setViewName("Lblog");
		
		return mv;
	}
	
	@GetMapping("/editor")
	public ModelAndView getEditorView(@RequestParam("username") String username, ModelAndView mv) {
		
		mv.addObject("username", username);
		mv.setViewName("editor");
		
		return mv;
	}
	
	@PostMapping("/editor")
	public ModelAndView editor(
		@RequestParam("username") String username,
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
		
		mv.addObject("blogs", blogInfoRepository.findAll());
		System.out.println("username: "+ username);
		mv.addObject("username", username);
		mv.setViewName("Lblog");
		
		return mv;
	}
	
	@GetMapping("/delete")
	public ModelAndView delete(
			@RequestParam("username") String username,//
			@RequestParam("blogId") long blogId,//
			ModelAndView mv
			) {
		
		mv.addObject("username", username);
		
		BlogInfo blogInfo = blogInfoRepository.findById(blogId).get();
		blogInfoRepository.delete(blogInfo);
		
		mv.addObject("blogs", blogInfoRepository.findAll());
		mv.setViewName("Lblog");
		
		return mv;
		
	}
	
	
	
}
