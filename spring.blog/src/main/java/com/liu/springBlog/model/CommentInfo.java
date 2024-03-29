package com.liu.springBlog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long blogId;
	private Long userId;
	private String comment;

}
