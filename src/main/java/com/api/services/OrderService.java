package com.api.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.api.entities.Order;

@Service
public interface OrderService {

	public List<Order> getAllOrders();    
    public Order getOrderById(Integer id); 
	public String createOrder(Order order); 
	public String deleteOrder(Integer id);    
   
}
