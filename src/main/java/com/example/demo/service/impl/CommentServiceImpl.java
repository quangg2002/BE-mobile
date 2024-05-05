package com.example.demo.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentImage;
import com.example.demo.repository.CommentImageRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CommentImageRepository commentImageRepository;

	@Override
	public List<Comment> findCommentsByStoreId(Long storeId) {
		return commentRepository.findByStoreId(storeId);
	}

	@Override
	public Comment putComment(Long commentId, String comment) {
		java.util.Date utilDate = java.util.Calendar.getInstance().getTime();
		Date sqlDate = new Date(utilDate.getTime());
		Comment cmt = commentRepository.findById(commentId).orElse(null);
		cmt.setDate(sqlDate);
		cmt.setComment(comment);
		return commentRepository.save(cmt);
	}

	@Override
	public Comment postComment(Long userId, Long storeId, String commentText, int rating, MultipartFile[] imageFiles)
			throws Exception {
		java.util.Date utilDate = java.util.Calendar.getInstance().getTime();
		Date sqlDate = new Date(utilDate.getTime());

		Comment newComment = new Comment();
		// Thiết lập các trường cho đánh giá
		newComment.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
		newComment.setStore(storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Store not found")));
		newComment.setComment(commentText);
		newComment.setRating(rating);
		newComment.setDate(sqlDate);

		// Lưu đánh giá vào CSDL
		newComment = commentRepository.save(newComment);
		if (imageFiles != null && imageFiles.length > 0) {
			List<CommentImage> commentImages = new ArrayList<>();

			// Lưu ảnh vào cơ sở dữ liệu
			for (MultipartFile imageFile : imageFiles) {
				String base64Image = saveImage(imageFile);
				CommentImage commentImage = new CommentImage();
				commentImage.setComment(newComment);
				commentImage.setImage(base64Image);

				// Lưu CommentImage vào CSDL
				commentImage = commentImageRepository.save(commentImage);

				// Thêm vào danh sách CommentImage của đánh giá
				commentImages.add(commentImage);
				System.out.println(commentImage);
			}

			newComment.setCommentImages(commentImages);
		}

		return newComment;
	}

	public String saveImage(MultipartFile imageFile) throws IOException {
		// Đọc nội dung của file ảnh
		byte[] bytes = imageFile.getBytes();
		// Chuyển đổi thành base64
		String base64Image = Base64.getEncoder().encodeToString(bytes);
		return base64Image;
	}

	@Override
	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}
}
