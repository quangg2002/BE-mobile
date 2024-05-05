package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.entity.Store;
import com.example.demo.service.CommentImageService;
import com.example.demo.service.CommentService;
import com.example.demo.service.StoreService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class StoreController {
	
	@Autowired
	private StoreService storeService;

	@GetMapping("/top-rated-stores")
    public List<Store> getTopRatedStores() {
        return storeService.getTopRatedStores(); 
    }
}
