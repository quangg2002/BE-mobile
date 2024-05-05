package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CommentImage;

public interface CommentImageRepository extends JpaRepository<CommentImage, Long> {
	List<CommentImage> findByCommentId(Long CommentId);
	
	void deleteByCommentId(Long commentId);
}
