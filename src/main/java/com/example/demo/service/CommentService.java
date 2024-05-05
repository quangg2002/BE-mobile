package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.Comment;

@Service
public interface CommentService {
	List<Comment> findCommentsByStoreId(Long storeId);
	
	Comment putComment(Long commentId, String comment);
	
	Comment postComment(Long userId, Long storeId, String comment, int rating, MultipartFile[] imageFiles) throws Exception;
	
	void deleteComment(Long commentId);
}
