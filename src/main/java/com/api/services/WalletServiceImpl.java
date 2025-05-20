package com.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entities.User;
import com.api.exceptions.InsufficientBalanceException;
import com.api.repository.UserRepo;

@Service
public class WalletServiceImpl implements WalletService{
	
	
	@Autowired
    private UserRepo userRepo;

	@Override
	public double getWalletBalance(Integer userId) {
		  User user = userRepo.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        return user.getWalletBalance();
	}

	@Override
	public User rechargeWallet(Integer userId, double amount) {
		 if (amount <= 0) {
	            throw new IllegalArgumentException("Amount must be greater than zero");
	        }

	        User user = userRepo.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        user.setWalletBalance(user.getWalletBalance() + amount);
	        return userRepo.save(user);
	}

	@Override
	public User deductAmount(Integer userId, Double amount) {
	
		Double balance = getWalletBalance(userId);

	    if (balance >= amount) {
	        Double newBalance = balance - amount;
	        User user = userRepo.findById(userId).orElseThrow();
	        user.setWalletBalance(newBalance);
	        userRepo.save(user);
	        return user;
	    } else {
	        throw new InsufficientBalanceException("Insufficient balance");
	    }
	    
		
	}

	

}
