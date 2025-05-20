package com.api.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.entities.Admin;
import com.api.entities.Astrologer;
import com.api.entities.User;
import com.api.exceptions.CustomNotFoundException;
import com.api.repository.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
    private AdminRepo adminRepo;

    @Override
    public Admin getById(Integer id) {
        return adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    @Override
    public Admin update(Integer id, Admin admin) {
        Admin existingAdmin = adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));

        existingAdmin.setName(admin.getName());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setPassword(admin.getPassword());

        return adminRepo.save(existingAdmin);
    }

    @Override
	public Admin getAdminByEmail(String email) {
    	return adminRepo.findByEmail(email)
	               .orElseThrow(() -> new CustomNotFoundException("Admin not found with email: " + email));

	}
	
	
    
    
	@Override
	public Admin loginAdmin(String email, String password) {
		 return adminRepo.findByEmail(email)
                 .filter(user -> user.getPassword().equals(password))
                 .orElse(null);
		
	}

	@Override
	public Admin changePassword(String email, String oldPassword, String newPassword) {
		Admin admin = getAdminByEmail(email);
		    if (admin.getPassword().equals(oldPassword)) {
		        admin.setPassword(newPassword);
		        return adminRepo.save(admin); // returns updated User
		    } else {
		        return null; // or throw new IllegalArgumentException("Invalid old password");
		    }
	}

	@Override
	public void uploadAdminImage(Integer id, MultipartFile imageFile) throws IOException {
		
		
		Admin admin = adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));

        // Upload folder
        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        // Save file
        String fileName = imageFile.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.write(filePath, imageFile.getBytes());

        // Update image name
        admin.setImage(fileName);
        adminRepo.save(admin);
		
	}

	

}
