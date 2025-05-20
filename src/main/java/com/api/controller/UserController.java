<<<<<<< HEAD
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
=======
package com.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.api.entities.User;
import com.api.exceptions.CustomNotFoundException;
import com.api.services.UserService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    // ✅ Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getById(@PathVariable Integer userId) {
        User user = userService.getById(userId);
        if (user == null) {
            throw new CustomNotFoundException("User not found with ID: " + userId);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // ✅ Add new user with validation
    @PostMapping
  
    public ResponseEntity<User> addUser(@Validated @RequestBody User user) {
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // ✅ Update existing user with validation
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId, @Validated @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // ✅ Delete user by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable Integer userId) {
        String status = userService.deleteById(userId);
        if (status == null) {
            throw new CustomNotFoundException("User not found with ID: " + userId);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
    
    @GetMapping("/image/{imageName}")
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        Path path = Paths.get("uploads/" + imageName);
        if (Files.exists(path)) {
            response.setContentType(Files.probeContentType(path));
            Files.copy(path, response.getOutputStream());
            response.getOutputStream().flush();
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PostMapping("/{userId}/upload-image")
    public ResponseEntity<String> uploadUserImage(
            @PathVariable Integer userId,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

    	userService.uploadUserImage(userId, imageFile);

        return ResponseEntity.ok("Image uploaded and user updated successfully for user ID: " + userId);
    }


    
}
>>>>>>> 6565aec (Updated the file with latest changes)
