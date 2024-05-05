package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FavoriteStore;
import com.example.demo.entity.Store;
import com.example.demo.entity.User;
import com.example.demo.repository.FavoriteStoreRepository;
import com.example.demo.service.FavoriteStoreService;

@Service
public class FavoriteStoreImpl implements FavoriteStoreService{

	@Autowired
    private FavoriteStoreRepository favoriteStoreRepository;

    @Override
    public void addFavorite(User user, Store store) {
        FavoriteStore favoriteStore = new FavoriteStore();
        favoriteStore.setUser(user);
        favoriteStore.setStore(store);
        favoriteStoreRepository.save(favoriteStore);
    }

    @Override
    public void removeFavorite(User user, Store store) {
        FavoriteStore favoriteStore = favoriteStoreRepository.findByUserAndStore(user, store);
        if (favoriteStore != null) {
            favoriteStoreRepository.delete(favoriteStore);
        }
    }
}
