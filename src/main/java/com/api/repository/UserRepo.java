package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{

}
