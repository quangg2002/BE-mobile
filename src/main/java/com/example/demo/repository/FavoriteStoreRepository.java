package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.FavoriteStore;
import com.example.demo.entity.Store;
import com.example.demo.entity.User;

public interface FavoriteStoreRepository extends JpaRepository<FavoriteStore, Long>{
	FavoriteStore findByUserAndStore(User user, Store store);

	List<FavoriteStore> findByUser(User user);
}
