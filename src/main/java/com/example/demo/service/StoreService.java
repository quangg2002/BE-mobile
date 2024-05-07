package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Store;

public interface StoreService {
	List<Store> getTopRatedStores();

	List<Store> getAllStores();
}
