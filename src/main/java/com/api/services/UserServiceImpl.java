package com.api.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.api.entities.User;
import com.api.exceptions.CustomNotFoundException;
import com.api.repository.UserRepo;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserRepo userRepo;

	//AllUsersGet
	@Override
	public List<User> getAllUsers() {	
		return userRepo.findAll();	
	}

	//getById
	@Override
	public User getById(Integer userId) {
		
		Optional<User> findById = userRepo.findById(userId);
		if(findById.equals(findById)) {
			return findById.get();		
		  }
		return null;	
	}

	//insertUser
	@Override
	public User addUser(User user) {
		return userRepo.save(user);
		
	}

	//update user
	@Override
	public User updateUser(Integer userId,User user) {
		 User existingUser = userRepo.findById(userId)
	                .orElseThrow(() -> new CustomNotFoundException("User not found with ID: " + userId));
		    existingUser.setName(user.getName());
	        existingUser.setAge(user.getAge());
	        existingUser.setEmail(user.getEmail());
	        existingUser.setDateOfBirth(user.getDateOfBirth());
	        existingUser.setTimeOfBirth(user.getTimeOfBirth());
	        existingUser.setContactNumber(user.getContactNumber());
	        existingUser.setPlace(user.getPlace());
	        existingUser.setGender(user.getGender());

	        // Password ko update karne se pehle validate kar sakte hain
	        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
	            existingUser.setPassword(user.getPassword());
	        }
	        return userRepo.save(existingUser);
	}

	//delete user
	@Override
	public String deleteById(Integer userId) {	
		if(userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			return "Successfully Delete";
		}
		 else {
		        throw new CustomNotFoundException("User not found with ID: " + userId);
		    }
	}

    
	// Given an email, fetch the user from the database.
	// If user not found, throw a CustomNotFoundException with a proper message.
	@Override
	public User getUserByEmail(String email) {
		
		return userRepo.findByEmail(email)
	               .orElseThrow(() -> new CustomNotFoundException("User not found with email: " + email));

	}

	//login user
	public User loginUser(String email, String password) {
	    return userRepo.findByEmail(email)
	                   .filter(user -> user.getPassword().equals(password))
	                   .orElse(null);
	}

	//change password
	@Override
	public User changePassword(String email, String oldPassword, String newPassword) {
		
		 User user = getUserByEmail(email);
		    if (user.getPassword().equals(oldPassword)) {
		        user.setPassword(newPassword);
		        return userRepo.save(user); // returns updated User
		    } else {
		        return null; // or throw new IllegalArgumentException("Invalid old password");
		    }
		
	}

	@Override
	public void uploadUserImage(Integer userId, MultipartFile imageFile) throws IOException {
	
		User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Upload folder
        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        // Save file
        String fileName = imageFile.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.write(filePath, imageFile.getBytes());

        // Update image name
        user.setImageName(fileName);
        userRepo.save(user);
		
	}

	
	

}
