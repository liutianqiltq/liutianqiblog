package com.liu.springBlog.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.liu.springBlog.model.BlogInfo;
import com.liu.springBlog.model.UserInfo;
import com.liu.springBlog.repository.BlogInfoRepository;
import com.liu.springBlog.repository.UserInfoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserInfoRepository userInfoRepository;
	@MockBean
	private BlogInfoRepository blogInfoRepository;
	
	@Test
	public void getLoginView_Success() throws Exception{
		
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/")//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
				.andExpect(view().name("login"));
	}
	
	@Test
	public void blog_success() throws Exception{
		
		String username = "admin";
		String password = "admin";
		UserInfo userInfo = UserInfo.builder()//
				.name(username)//
				.password(password)//
				.build();
		BlogInfo blogInfo = BlogInfo.builder()//
				.blogId(1l)//
				.userId(1l)//
				.title("title")//
				.introduction("introduction")//
				.contents("contents")//
				.build();
		
		when(userInfoRepository.findByName(username)).thenReturn(userInfo);
		List<BlogInfo> blogs = blogInfoRepository.findAll();
		when(blogInfoRepository.findAll()).thenReturn(blogs);
		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/")//
				.param("username", username)//
				.param("password", password)//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
				.andExpect(view().name("Lblog"));
	}
	
	@Test
	public void blog_fail1() throws Exception{
		
		String username = "admin";
		String password = "admin";
		UserInfo userInfo = UserInfo.builder()//
				.name(username)//
				.password(password)//
				.build();
		
		when(userInfoRepository.findByName(username)).thenReturn(null);
		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/")//
				.param("username", username)//
				.param("password", password)//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
				.andExpect(view().name("fail"));
		
	}
	
	@Test
	public void blog_fail2() throws Exception{
		
		String username = "admin";
		String password = "admin";
		UserInfo userInfo = UserInfo.builder()//
				.name(username)//
				.password(password)//
				.build();
		
		when(userInfoRepository.findByName(username)).thenReturn(null);
		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/")//
				.param("username", username)//
				.param("password", "aaa")//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
				.andExpect(view().name("fail"));
	}

}
