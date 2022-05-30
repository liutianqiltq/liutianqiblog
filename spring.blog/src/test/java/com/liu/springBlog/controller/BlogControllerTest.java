package com.liu.springBlog.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

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
import com.liu.springBlog.model.CommentInfo;
import com.liu.springBlog.model.UserInfo;
import com.liu.springBlog.repository.BlogInfoRepository;
import com.liu.springBlog.repository.CommentInfoRepository;
import com.liu.springBlog.repository.UserInfoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BlogInfoRepository blogInfoRepository;
	@MockBean
	private UserInfoRepository userInfoRepository;
	@MockBean
	private CommentInfoRepository commentInfoRepository;
	
	@Test
	public void testGetBlogView_Success() throws Exception{
		
		String username = "admin";
		
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/blog")//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
		.andExpect(view().name("Lblog"));
	}
	
	@Test
	public void testGetEditorView_Success() throws Exception{
		
		String username = "admin";
		UserInfo userInfo = UserInfo.builder()//
				.name(username)//
				.build();
		
		when(userInfoRepository.findByName(username)).thenReturn(userInfo);
		
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/editor")//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);
		
		
		mockMvc.perform(request)//
				.andExpect(view().name("editor"));
	}
	
	@Test
	public void testEditorSuccess() throws Exception {
		
		String username = "admin";
		String title = "admin";
		String introduction = "admin";
		String contents = "admin";
		BlogInfo blogInfo = BlogInfo.builder()//
				.title(title)//
				.introduction(introduction)//
				.contents(contents)//
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/editor")//
				.param("username",username)//
				.param("title", title)//
				.param("introduction",introduction)//
				.param("contents",contents)//
				.accept(MediaType.APPLICATION_JSON);
		
		when(blogInfoRepository.save(blogInfo)).thenReturn(blogInfo);
				
		mockMvc.perform(request)//
				.andExpect(view().name("Lblog"));
		
	}
	
	@Test
	public void testDeleteSuccess() throws Exception{
		
		String username = "admin";
		Long blogId = (long) 1;
		BlogInfo blogInfo = BlogInfo.builder()//
				.blogId(blogId)//
				.userId(1l)//
				.title("title")//
				.introduction("introduction")//
				.contents("contents")//
				.build();
		
		when(blogInfoRepository.findById(blogId)).thenReturn(Optional.of(blogInfo));
		
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/delete")//
				.param("username", username)//
				.param("blogId", ""+blogId)//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
				.andExpect(view().name("Lblog"));
	}
	
	@Test
	public void testGetCommentView_Success() throws Exception{
		
		String username = "admin";
		UserInfo userInfo = UserInfo.builder()//
				.name(username)//
				.build();
		
		when(userInfoRepository.findByName(username)).thenReturn(userInfo);
		
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/comment")//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);
		
		
		mockMvc.perform(request)//
				.andExpect(view().name("comment"));
	}
	
	@Test
	public void testCommentSuccess() throws Exception {
		
		String username = "admin";
		String comment = "admin";
		CommentInfo commentInfo = CommentInfo.builder()//
				.comment(comment)//
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/comment")//
				.param("username",username)//
				.param("comment",comment)//
				.accept(MediaType.APPLICATION_JSON);
		
		when(commentInfoRepository.save(commentInfo)).thenReturn(commentInfo);
				
		mockMvc.perform(request)//
				.andExpect(view().name("Lblog"));
		
	}
	
	@Test
	public void testGetUpdateView_Success() throws Exception{
		
		String username = "admin";
		Long blogId = (long) 1;
		BlogInfo blogInfo = BlogInfo.builder()//
				.blogId(blogId)//
				.userId(1l)//
				.title("title")//
				.introduction("introduction")//
				.contents("contents")//
				.build();
		
		when(blogInfoRepository.findById(blogId)).thenReturn(Optional.of(blogInfo));
		
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/update")//
				.param("username", username)//
				.param("blogId", ""+blogId)
				.accept(MediaType.APPLICATION_JSON);
		
		
		mockMvc.perform(request)//
				.andExpect(view().name("update"));
	}
	
	@Test
	public void testUpdateSuccess() throws Exception {
		
		String username = "admin";
		Long blogId = (long) 1;
		String title = "admin";
		String introduction = "admin";
		String contents = "admin";
		BlogInfo blogInfo = BlogInfo.builder()//
				.blogId(1l)//
				.userId(1l)//
				.title("title")//
				.introduction("introduction")//
				.contents("contents")//
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("username",username)//
				.param("blogId", ""+blogId)//
				.param("title", title)//
				.param("introduction",introduction)//
				.param("contents",contents)//
				.accept(MediaType.APPLICATION_JSON);
		
		when(blogInfoRepository.findById(blogId)).thenReturn(Optional.of(blogInfo));
				
		mockMvc.perform(request)//
				.andExpect(view().name("Lblog"));
		
	}
	
}
