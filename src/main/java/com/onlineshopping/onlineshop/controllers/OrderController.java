package com.onlineshopping.onlineshop.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onlineshopping.onlineshop.CustomResponse;
import com.onlineshopping.onlineshop.models.OrderModel;
import com.onlineshopping.onlineshop.services.OrderRepo;



@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderRepo orderRepo;
	
	@GetMapping({"", "/"})
	public ResponseEntity<?> index() {
		List<OrderModel> orders = orderRepo.findAll();
		if(!orders.isEmpty()) {
			return ResponseEntity.ok(orders);			
		} else {
			CustomResponse error = new CustomResponse("No orders found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrder(@PathVariable("id") int id) {
		final OrderModel order = orderRepo.findById(id).orElse(null);
		if (order == null) {
			CustomResponse error = new CustomResponse("Order not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		else {
			return ResponseEntity.ok(order);
		}
	}
	
	@PostMapping({"", "/"})
	public ResponseEntity<?> createOrder(@RequestBody OrderModel order) {
		orderRepo.save(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
	
}