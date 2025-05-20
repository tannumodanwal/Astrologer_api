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

import com.api.DTO.AdminLoginRequest;
import com.api.entities.Admin;
import com.api.services.AdminService;

@RestController
@RequestMapping("/api/authAdmin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminAuthController {

	@Autowired
    private AdminService adminService;
	
	 
    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody AdminLoginRequest adminloginRequest) {
    	Admin admin = adminService.loginAdmin(adminloginRequest.getEmail(), adminloginRequest.getPassword());
        
        if (admin != null) {
            return ResponseEntity.ok(admin); // ✅ Ye admin object frontend me bhejega
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please correct fill the email & password......");
        }
    }

    
    
 // Registration endpoint that returns the registered user object
    @PostMapping("/register")
    public ResponseEntity<Admin> register(@Validated @RequestBody Admin admin) {
        try {
            Admin savedAdmin= adminService.addAdmin(admin);  // save admin
            return ResponseEntity.ok(savedAdmin);         // return full admin object
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


	
}
