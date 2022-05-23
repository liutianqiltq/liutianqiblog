package com.liu.springBlog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long blog_id;
	private Long user_id;
	private String title;
	private String introduction;
	private String contents;
	
}
