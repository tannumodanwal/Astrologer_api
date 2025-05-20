package com.api.services;

import org.springframework.stereotype.Service;

import com.api.entities.User;



@Service
public interface WalletService {

	public double getWalletBalance(Integer userId);
	
	public User rechargeWallet(Integer userId, double amount);
	
	public User deductAmount(Integer userId, Double amount);
	
}
