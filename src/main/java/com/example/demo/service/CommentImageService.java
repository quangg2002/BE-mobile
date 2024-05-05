package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CommentImage;

@Service
public interface CommentImageService {
	List<CommentImage> findCommentImageByCommentId(Long commentID);
	
	void deleteCommentImage(Long commentId);
}
