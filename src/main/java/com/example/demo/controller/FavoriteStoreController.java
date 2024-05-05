package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.FavoriteStore;
import com.example.demo.entity.Store;
import com.example.demo.entity.User;
import com.example.demo.repository.FavoriteStoreRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FavoriteStoreService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FavoriteStoreController {
	@Autowired
	private FavoriteStoreRepository favoriteStoreRepository;

	@Autowired
	private FavoriteStoreService favoriteStoreService;

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/favorite")
	public String toggleFavorite(@RequestParam String userId, @RequestParam String storeId) {
		User user = userRepository.findById(Long.valueOf(userId)).orElse(null);
		if (user == null) {
			return "Người dùng không tồn tại.";
		}

		Store store = storeRepository.findById(Long.valueOf(storeId)).orElse(null);
		if (store == null) {
			return "Cửa hàng không tồn tại.";
		}

		FavoriteStore existingFavorite = favoriteStoreRepository.findByUserAndStore(user, store);
		if (existingFavorite != null) {
			favoriteStoreService.removeFavorite(user, store);
			return "Yêu thích đã được huỷ bỏ.";
		} else {
			favoriteStoreService.addFavorite(user, store);
			return "Yêu thích đã được thêm vào.";
		}
	}
	
	@GetMapping("/favorite-stores/{userId}")
	public List<Store> getFavoriteStoresByUserId(@PathVariable Long userId) {
	    
	    User user = userRepository.findById(userId).orElse(null);

	    if (user == null) {
	        return Collections.emptyList(); 
	    }

	    List<FavoriteStore> favoriteStores = favoriteStoreRepository.findByUser(user);
	    
	    List<Store> stores = new ArrayList<>();
	    for (FavoriteStore favoriteStore : favoriteStores) {
	        stores.add(favoriteStore.getStore());
	    }
	    
	    return stores;
	}
	
	@GetMapping("/is-favorite/{userId}/{storeId}")
	public boolean isFavoriteStore(@PathVariable Long userId, @PathVariable Long storeId) {
	    // Tìm kiếm người dùng và cửa hàng trong cơ sở dữ liệu
	    User user = userRepository.findById(userId).orElse(null);
	    Store store = storeRepository.findById(storeId).orElse(null);

	    // Kiểm tra xem người dùng và cửa hàng có tồn tại không
	    if (user == null || store == null) {
	        return false;
	    }

	    // Kiểm tra xem có bản ghi yêu thích nào cho cặp người dùng và cửa hàng này không
	    FavoriteStore existingFavorite = favoriteStoreRepository.findByUserAndStore(user, store);
	    
	    // Trả về kết quả dựa trên việc có hay không có bản ghi yêu thích
	    return existingFavorite != null;
	}
}
