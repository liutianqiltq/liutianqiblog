package com.liu.springBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liu.springBlog.model.CommentInfo;
import com.liu.springBlog.model.UserInfo;

public interface CommentInfoRepository extends JpaRepository<CommentInfo, Long>{

}
