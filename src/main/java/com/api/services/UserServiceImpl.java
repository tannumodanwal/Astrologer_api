package com.api.services;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.entities.User;
import com.api.exceptions.CustomNotFoundException;
import com.api.repository.UserRepo;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public List<User> getAllUsers() {
		
		return userRepo.findAll();
	}

	@Override
	public User getById(Integer userId) {
		Optional<User> findById = userRepo.findById(userId);
		if(findById.equals(findById)) {
			return findById.get();		
		  }
		return null;
	}

	@Override
	public String addUser(User user) {
		userRepo.save(user);
		return "Successfully Insert!";
	}

	@Override
	public String updateUser(User user) {
		userRepo.save(user);
		return "Successfully Update!";
	}

	@Override
	public String deleteById(Integer userId) {
		if(userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			return "Successfully Delete";
		}
		 else {
		        throw new CustomNotFoundException("Order not found with ID: " + userId);
		    }
	}

}
