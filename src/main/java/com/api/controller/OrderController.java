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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.entities.Order;
import com.api.services.OrderService;



@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
		 String savedOrder = orderService.createOrder(order);
	        return new ResponseEntity<>(savedOrder,HttpStatus.CREATED);
	}

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
	
}
=======
package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.DTO.BookRequest;
import com.api.entities.Order;
import com.api.entities.User;
import com.api.exceptions.CustomNotFoundException;
import com.api.services.OrderService;



@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

	
	@Autowired
	private OrderService orderService;
	
	
	@PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
		 String savedOrder = orderService.createOrder(order);
	        return new ResponseEntity<>(savedOrder,HttpStatus.CREATED);
	}

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
    	Order order = orderService.getOrderById(id);
        if (order == null) {
            throw new CustomNotFoundException("User not found with ID: " + id);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
    	String status = orderService.deleteOrder(id);
        if (status == null) {
            throw new CustomNotFoundException("Order not found with ID: " + id);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
	
    
 // 1. Book Order with minimum wallet check
    @PostMapping("/book")
    public ResponseEntity<Order> bookOrder(@RequestBody BookRequest request) {
        Order order = orderService.bookOrder(
            request.getUserId(),
            request.getAstrologerId(),
            request.getCommunicationType()
        );
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    // 2. Start Chat
    @PostMapping("/{orderId}/start")
    public ResponseEntity<Order> startChat(@PathVariable Integer orderId) {
        Order order = orderService.startChat(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // 3. End Chat and deduct wallet
    @PostMapping("/{orderId}/end")
    public ResponseEntity<Order> endChat(@PathVariable Integer orderId) {
        Order order = orderService.endChat(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


	
}
>>>>>>> 6565aec (Updated the file with latest changes)
