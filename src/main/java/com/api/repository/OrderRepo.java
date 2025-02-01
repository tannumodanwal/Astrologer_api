package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entities.Order;

public interface OrderRepo extends JpaRepository<Order,Integer>{

}
