package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.DTO.LoginRequest;
import com.api.entities.User;

import com.api.services.UserService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	  @Autowired
	    private UserService userService;
	    
	    
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginRequest) {
	        User user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
	        
	        if (user != null) {
	        	
	        	 if (user.getImageName() != null && !user.getImageName().isEmpty()) {
	                 String baseUrl = "http://localhost:9999/images/";
	                 user.setImageName(baseUrl + user.getImageName());
	             }
	        	
	        	
	            return ResponseEntity.ok(user); // ✅ Ye user object frontend me bhejega
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The email address you entered is not registered!!. Please sign up first.");
	        }
	    }

	    
	    
	 // Registration endpoint that returns the registered user object
	    @PostMapping("/register")
	    public ResponseEntity<User> register(@Validated @RequestBody User user) {
	        try {
	            User savedUser = userService.addUser(user);  // save user
	            return ResponseEntity.ok(savedUser);         // return full user object
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }



}
