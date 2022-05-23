package com.liu.springBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liu.springBlog.model.BlogInfo;


public interface BlogInfoRepository extends JpaRepository<BlogInfo, Long>{
	
	BlogInfo findByTitle(String title);

}
