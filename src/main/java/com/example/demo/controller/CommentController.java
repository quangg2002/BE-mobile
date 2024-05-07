package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentImage;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentImageService;
import com.example.demo.service.CommentService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class CommentController {
	
	@GetMapping("/test")
	public String ok() {
		return "ok ro`i";
	}
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	CommentImageService commentImageService;
	
//	@GetMapping("/search/{commentId}")
//	public List<CommentImage> search(@PathVariable Long commentId) {
//		return commentImageService.findCommentImageByCommentId(commentId);
//	}
	@GetMapping("/search/{commentId}")
	public Comment search(@PathVariable Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}
	
	@GetMapping("/comments/{storeId}")
	public List<Comment> getBookComments(@PathVariable String storeId) {
		return commentService.findCommentsByStoreId(Long.valueOf(storeId));
	}

	@PostMapping("/comment/{storeId}/{userId}")
	public Comment postComment(@PathVariable Long storeId,
	                           @PathVariable Long userId,
	                           @RequestParam("comment") String commentText,
	                           @RequestParam("rating") String rating,
	                           @RequestParam(value = "images", required = false) MultipartFile[] imageFiles) throws Exception {
	    // System.out.println(comment);
	    Comment newComment = commentService.postComment(userId, storeId, commentText, Integer.valueOf(rating), imageFiles);
	    return newComment;
	}

	
	@PutMapping("/comments/{commentId}")
	public Comment putComment(@PathVariable Long commentId, @RequestBody Comment comment) {
		return commentService.putComment(commentId, comment.getComment());
	}
	
	@DeleteMapping("/comments/{commentId}")
	public void deleteComment(@PathVariable String commentId) {
		commentService.deleteComment(Long.valueOf(commentId));
		commentImageService.deleteCommentImage(Long.valueOf(commentId));
	}
}

