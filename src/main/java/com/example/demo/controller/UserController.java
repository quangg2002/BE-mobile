package com.example.demo.controller;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
	@Autowired
    private UserService userService;

	@PostMapping("/login")
    public User login(@RequestBody User user) {
        User existingUser = userService.findByPhoneNumber(user.getPhoneNumber());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
        	System.out.println(existingUser.getId() + " " + existingUser.getName() + " " + existingUser.getPhoneNumber());
            return existingUser;
        } else {
            return null;
        }
    }
	
//	public String generateAccessToken(User user) {
//	    byte[] bytes = new byte[64];
//	    new SecureRandom().nextBytes(bytes);
//	    String accessToken = Base64.getEncoder().encodeToString(bytes);
//	    
//	    return accessToken;
//	}
}
