package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Store;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.StoreService;

@Service
public class StoreImpl implements StoreService{
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	public void updateAverageRatingsForAllStores() {
	    // Find all stores
	    List<Store> allStores = storeRepository.findAll();

	    // Calculate average rating for each store
	    Map<Store, Double> storeAverageRatings = allStores.stream()
	            .collect(Collectors.toMap(
	                    store -> store,
	                    this::calculateAverageRating
	            ));

	    // Update averageRating in database for all stores
	    for (Map.Entry<Store, Double> entry : storeAverageRatings.entrySet()) {
	        Store store = entry.getKey();
	        Double averageRating = entry.getValue();
	        store.setAverageRating(averageRating);
	        storeRepository.save(store); // Lưu thay đổi vào cơ sở dữ liệu
	    }
	}

	
	public List<Store> getTopRatedStores() {
		updateAverageRatingsForAllStores();
		
	    // Find top 5 stores sorted by average rating
	    List<Store> top5Stores = storeRepository.findTop5ByOrderByAverageRatingDesc();

	    return top5Stores;
	}


	private double calculateAverageRating(Store store) {
        List<Comment> comments = commentRepository.findByStoreId(store.getId());

        if (comments.isEmpty()) {
            return 0.0;
        }

        int totalRating = comments.stream().mapToInt(Comment::getRating).sum();
        return (double) totalRating / comments.size();
    }
}
