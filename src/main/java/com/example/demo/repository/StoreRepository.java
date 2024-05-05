package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long>{

	List<Store> findTop5ByOrderByAverageRatingDesc();
	
}