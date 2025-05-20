package com.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entities.Admin;
import com.api.entities.User;

public interface AdminRepo extends JpaRepository<Admin,Integer>{

	
	Optional<Admin> findByEmail(String email);
}
