package com.api.controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.entities.User;
import com.api.services.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	//Get All Data
	@GetMapping
	public ResponseEntity <List<User>> getAllUsers(){	  
		List<User> allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers,HttpStatus.OK);
		
	}
	
	
	
	//Get Data By Id
	@GetMapping("/{userId}")
	public ResponseEntity<User> getById(@PathVariable Integer userId) {
	User user =  userService.getById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	//insert data
	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody User user) {	
		String status = userService.addUser(user);
		return new ResponseEntity<>(status,HttpStatus.CREATED);	
	}
	
	//update data
	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody User user){
		String status = userService.updateUser(user);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	//DeleteDataById
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer userId){	
		String status = userService.deleteById(userId);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
}