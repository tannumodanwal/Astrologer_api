package com.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.entities.User;


@Service
public interface UserService {

	public List<User> getAllUsers();
	
	public User getById(Integer userId);
	
	public String addUser(User user);
	
	public String updateUser(User user);
	
	public String deleteById(Integer userId);
	
}
