package com.api.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.entities.Admin;



@Service 
public interface AdminService {

	public Admin getById(Integer id);
	
	public Admin addAdmin(Admin admin);
	
	public Admin update(Integer id,Admin admin);
	
	public Admin getAdminByEmail(String email);
	
	public Admin loginAdmin(String email, String password);
	
	public Admin changePassword(String email, String oldPassword, String newPassword);
	
	public void uploadAdminImage(Integer id, MultipartFile imageFile) throws IOException;
	
}
