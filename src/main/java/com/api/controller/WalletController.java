package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.DTO.RechargeRequest;
import com.api.entities.User;
import com.api.services.WalletService;

@RestController
@RequestMapping("/api/wallet")
@CrossOrigin(origins = "http://localhost:5173")
public class WalletController {

	
	    @Autowired
	    private WalletService walletService;

	    @GetMapping("/balance/{userId}")
	    public ResponseEntity<Double> getBalance(@PathVariable Integer userId) {
	        double balance = walletService.getWalletBalance(userId);
	        return ResponseEntity.ok(balance);
	    }

	    @PostMapping("/recharge")
	    public ResponseEntity<User> recharge(@RequestBody RechargeRequest request) {
	        User user = walletService.rechargeWallet(request.getUserId(), request.getAmount());
	        return ResponseEntity.ok(user);
	    }

	    
}
