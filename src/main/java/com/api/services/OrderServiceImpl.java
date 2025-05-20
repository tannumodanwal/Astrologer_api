<<<<<<< HEAD
package com.api.services;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.entities.Astrologer;
import com.api.entities.Order;
import com.api.entities.User;
import com.api.exceptions.CustomNotFoundException;
import com.api.repository.AstrologerRepo;
import com.api.repository.OrderRepo;
import com.api.repository.UserRepo;


@Service
public class OrderServiceImpl implements OrderService{

	
	 @Autowired
	 private OrderRepo orderRepo;
	
	 @Autowired
	 private UserRepo userRepo;

	 @Autowired
     private AstrologerRepo astrologerRepo;

	
	 //AddOrder
	 
	@Override
	public String createOrder(Order order) {
		 User user = userRepo.findById(order.getUserId())
                .orElseThrow(() -> new CustomNotFoundException("User not found"));

        Astrologer astrologer = astrologerRepo.findById(order.getAstrologerId())
                .orElseThrow(() -> new CustomNotFoundException("Astrologer not found"));

        order.setUserId(0);
        order.setAstrologerId(0);

     // Calculate duration
        
        if (order.getStartTime() != null && order.getEndTime() != null) {
            long durationInSeconds = java.time.Duration.between(order.getStartTime(), order.getEndTime()).getSeconds();
            if (durationInSeconds < 0) {
                throw new CustomNotFoundException("End time cannot be before start time.");
            }
            order.setDuration(durationInSeconds); // Set the calculated duration
        } else {
            throw new CustomNotFoundException("Start time and End time must not be null.");
        }
        
        
        // Save the order
       orderRepo.save(order);
       return "Successfully Insert Order!";
		
	}

	//GetAllOrders
	
	@Override
	public List<Order> getAllOrders() {
		
		return orderRepo.findAll();
	}

	//GetById
	
	@Override
	public Order getOrderById(Integer id) {
	
		Optional<Order> findById = orderRepo.findById(id);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
		
	}
	//DeleteById
	
	@Override
	public String deleteOrder(Integer id) {
		
		Optional<Order> existingAstrologer = orderRepo.findById(id);
	    if (existingAstrologer.isPresent()) {
	       orderRepo.deleteById(id);
	        return "Order successfully deleted!";
	    } else {
	        throw new CustomNotFoundException("Order not found with ID: " + id);
	    }
		
	}
}
=======
package com.api.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entities.Astrologer;
import com.api.entities.Order;
import com.api.entities.User;
import com.api.enums.CommunicationType;
import com.api.enums.OrderStatus;
import com.api.exceptions.CustomNotFoundException;
import com.api.repository.AstrologerRepo;
import com.api.repository.OrderRepo;
import com.api.repository.UserRepo;

@Service
public class OrderServiceImpl  implements OrderService{


	 @Autowired
	 private OrderRepo orderRepo;
	
	 @Autowired
	 private UserRepo userRepo;

	 @Autowired
    private AstrologerRepo astrologerRepo;


	
	@Override
	public List<Order> getAllOrders() {
		
		return orderRepo.findAll();

		
	}

	@Override
	public Order getOrderById(Integer id) {
	
		Optional<Order> findById = orderRepo.findById(id);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;

		
	}

	@Override
	public String createOrder(Order order) {
	    
		User user = userRepo.findById(order.getUserId())
	            .orElseThrow(() -> new CustomNotFoundException("User not found"));

	    Astrologer astrologer = astrologerRepo.findById(order.getAstrologerId())
	            .orElseThrow(() -> new CustomNotFoundException("Astrologer not found"));

	  
	    // Calculate duration
	    if (order.getStartTime() != null && order.getEndTime() != null) {
	        long durationInSeconds = java.time.Duration.between(order.getStartTime(), order.getEndTime()).getSeconds();
	        if (durationInSeconds < 0) {
	            throw new CustomNotFoundException("End time cannot be before start time.");
	        }
	        order.setDuration(0);
	    } else {
	        throw new CustomNotFoundException("Start time and End time must not be null.");
	    }
	    
	    // Save the order
	    orderRepo.save(order);
	    return "Successfully Inserted Order!";
		
	}

	@Override
	public String deleteOrder(Integer id) {
		

		Optional<Order> existingAstrologer = orderRepo.findById(id);
	    if (existingAstrologer.isPresent()) {
	       orderRepo.deleteById(id);
	        return "Order successfully deleted!";
	    } else {
	        throw new CustomNotFoundException("Order not found with ID: " + id);
	    }

		
	}

	@Override
	public Order bookOrder(Integer userId, Integer astrologerId, CommunicationType type) {
		 User user = userRepo.findById(userId).orElseThrow(() -> new CustomNotFoundException("User not found"));
		    Astrologer astro = astrologerRepo.findById(astrologerId).orElseThrow(() -> new CustomNotFoundException("Astrologer not found"));

		    double minAmount = 5 * astro.getPrice();
		    if (user.getWalletBalance() < minAmount) {
		        throw new RuntimeException("Insufficient wallet balance. Please recharge.");
		    }

		    Order order = new Order();
		    order.setUserId(userId);
		    order.setAstrologerId(astrologerId);
		    order.setCommunicationType(type);
		    order.setOrderDate(LocalDate.now()); 
		    order.setStatus(OrderStatus.BOOKED);
		    order.setRatePerMinute(astro.getPrice());
		    order.setAmountPaid(minAmount);
		    order.setDuration(0);

		    return orderRepo.save(order);
	}

	@Override
	public Order startChat(Integer orderId) {
		Order order = orderRepo.findById(orderId).orElseThrow();
	    order.setStartTime(LocalTime.now());
	    order.setStatus(OrderStatus.IN_PROGRESS);
	    return orderRepo.save(order);
	}

	@Override
	public Order endChat(Integer orderId) {
		Order order = orderRepo.findById(orderId).orElseThrow();
	    LocalTime end = LocalTime.now();

	    long duration = Duration.between(order.getStartTime(), end).toMinutes();
	    double used = duration * order.getRatePerMinute();

	    order.setEndTime(end);
	    order.setDuration(0);
	    order.setAmountUsed(used);
	    order.setStatus(OrderStatus.COMPLETED);

	    // Deduct from user wallet
	    User user = userRepo.findById(order.getUserId()).orElseThrow();
	    user.setWalletBalance(user.getWalletBalance() - used);
	    userRepo.save(user);

	    return orderRepo.save(order);
	}

	
}
>>>>>>> 6565aec (Updated the file with latest changes)
