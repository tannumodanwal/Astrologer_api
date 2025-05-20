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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.entities.Admin;
import com.api.entities.Astrologer;
import com.api.exceptions.CustomNotFoundException;
import com.api.services.AdminService;
import com.api.services.AstrologerService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {
      

    @Autowired
    private AdminService adminService;
	
    @Autowired
    private AstrologerService astrologerService;

    
	@GetMapping("/{adminId}")
    public ResponseEntity<Admin> getById(@PathVariable("adminId") Integer id) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            throw new CustomNotFoundException("Admin not found with ID: " + id);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    // ✅ Add new Admin with validation
    @PostMapping
  
    public ResponseEntity<Admin> addUser(@Validated @RequestBody Admin admin) {
        Admin savedAdmin = adminService.addAdmin(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    // ✅ Update existing user with validation
    @PutMapping("/{adminId}")
    public ResponseEntity<Admin> updateUser(@PathVariable Integer id, @Validated @RequestBody Admin admin) {
    	Admin updatedAdmin = adminService.update(id, admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
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

	    @PostMapping("/{adminId}/upload-image")
	    public ResponseEntity<String> uploadUserImage(
	            @PathVariable("adminId") Integer id,
	            @RequestParam("image") MultipartFile imageFile) throws IOException {

	    	adminService.uploadAdminImage(id, imageFile);
 
	        return ResponseEntity.ok("Image uploaded and user updated successfully for user ID: " + id);
	    }
	    
	    @GetMapping("/pending-astrologers")
	    public List<Astrologer> getPendingAstrologers() {
	        return astrologerService.getPendingAstrologers();
	    }
	
	    
	    @PutMapping("/verify-astrologer/{astrologerId}")
	    public ResponseEntity<Astrologer> verifyAstrologer(@PathVariable("astrologerId") Integer astrologerId) {
	        Astrologer approvedAstrologer = astrologerService.approveAstrologer(astrologerId);
	        return ResponseEntity.ok(approvedAstrologer);
	    }


}
