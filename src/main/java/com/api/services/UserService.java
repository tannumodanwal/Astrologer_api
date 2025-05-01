package com.api.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.entities.User;

@Service
public interface UserService {
    public List<User> getAllUsers();	
	public User getById(Integer userId);
	public User addUser(User user);	
	public User updateUser(Integer userId,User user);	
	public String deleteById(Integer userId);	
	public User getUserByEmail(String email);
	public User loginUser(String email, String password);	
	public User changePassword(String email, String oldPassword, String newPassword);
	void uploadUserImage(Integer userId, MultipartFile imageFile) throws IOException;
}
