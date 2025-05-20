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

import com.api.DTO.AstrologerLoginRequest;
import com.api.entities.Astrologer;
import com.api.services.AstrologerService;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/authastro")
public class AstrologerAuthController {

	
	@Autowired
	private AstrologerService astrologerService;
	
	  
    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody AstrologerLoginRequest astrologerloginRequest) {
        Astrologer astrologer = astrologerService.loginAstrologer(astrologerloginRequest.getEmail(), astrologerloginRequest.getPassword());
        
        if (astrologer != null) {
            return ResponseEntity.ok(astrologer); // ✅ Ye astrologer object frontend me bhejega
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The email address you entered is not registered!!. Please sign up first.");
        }
    }

    
    
 // Registration endpoint that returns the registered user object
    @PostMapping("/register")
    public ResponseEntity<Astrologer> register(@Validated @RequestBody Astrologer astrologer) {
        try {
        	Astrologer savedAstrologer = astrologerService.addAstrologer(astrologer);  // save user
            return ResponseEntity.ok(savedAstrologer);         // return full user object
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
