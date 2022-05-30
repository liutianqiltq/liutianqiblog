package com.liu.springBlog.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.liu.springBlog.model.UserInfo;
import com.liu.springBlog.repository.UserInfoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserInfoRepository userInfoRepository;
	
	@Test
	public void getRegisterView_Success() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/register")//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
				.andExpect(view().name("register"));
	}
	
	@Test
	public void register_success() throws Exception {
		
		String username = "admin";
		String password = "admin";
		String passwordAgain = "admin";
		UserInfo userInfo = UserInfo.builder()//
				.name(username)//
				.password(password)//
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username",username)//
				.param("password", password)//
				.param("password_again", passwordAgain)//
				.accept(MediaType.APPLICATION_JSON);
		
		when(userInfoRepository.save(userInfo)).thenReturn(userInfo);
				
		mockMvc.perform(request)//
				.andExpect(view().name("Lblog"));
		
	}
	
	@Test
	public void register_fail() throws Exception {
		
		String username = "admin";
		String password = "admin";
		String passwordAgain = "aaa";
		UserInfo userInfo = UserInfo.builder()//
				.name(username)//
				.password(password)//
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username",username)//
				.param("password", password)//
				.param("password_again", passwordAgain)//
				.accept(MediaType.APPLICATION_JSON);
		
		when(userInfoRepository.save(userInfo)).thenReturn(userInfo);
				
		mockMvc.perform(request)//
				.andExpect(view().name("fail"));
	}

}
