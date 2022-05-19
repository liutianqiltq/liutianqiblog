package com.liu.springBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liu.springBlog.model.BlogInfo;
import com.liu.springBlog.model.UserInfo;


public interface BlogInfoRepository extends JpaRepository<BlogInfo, Long>{
	

}
