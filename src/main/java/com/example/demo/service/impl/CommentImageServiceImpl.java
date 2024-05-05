package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CommentImage;
import com.example.demo.repository.CommentImageRepository;
import com.example.demo.service.CommentImageService;

@Service
public class CommentImageServiceImpl implements CommentImageService {
	
	@Autowired
	CommentImageRepository commentImageRepository;

	@Override
	public List<CommentImage> findCommentImageByCommentId(Long commentId) {
		return commentImageRepository.findByCommentId(commentId);
	}

	@Override
	public void deleteCommentImage(Long commentId) {
		commentImageRepository.deleteByCommentId(commentId);
	}
}
