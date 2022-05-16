package com.liu.springBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liu.springBlog.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{
	
	UserInfo findByName(String name);

}
