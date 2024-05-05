package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Store;
import com.example.demo.entity.User;

@Service
public interface FavoriteStoreService {
    void addFavorite(User user, Store store);
    void removeFavorite(User user, Store store);
}
