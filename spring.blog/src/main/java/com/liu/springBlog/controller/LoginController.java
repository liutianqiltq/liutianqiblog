package com.liu.springBlog.controller;

import java.util.List;

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

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

	// 接口实例化；核心代码
	@Autowired
	private BlogInfoRepository blogInfoRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;

	@GetMapping("/")
	public String getLoginView() {//

		log.info("index方法被执行");

		return "login";
	}

	@PostMapping("/")
	public ModelAndView blog(//
			@RequestParam("username") String username, //
			@RequestParam("password") String password, //
			ModelAndView mv) {

		log.info("username:{},password:{}", username, password);

		mv.addObject("username", username);

		// 连接数据库
		UserInfo userInfo = userInfoRepository.findByName(username);

		if (userInfo != null && password.equals(userInfo.getPassword())) {//
			List<BlogInfo> blogs = blogInfoRepository.findAll();
			mv.addObject("blogs", blogs);
			mv.setViewName("Lblog");
		} else {
			mv.setViewName("fail");
		}
		return mv;
	}
}
