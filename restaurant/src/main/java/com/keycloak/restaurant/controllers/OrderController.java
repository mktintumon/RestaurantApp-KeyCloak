package com.keycloak.restaurant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keycloak.restaurant.entities.Order;
import com.keycloak.restaurant.entities.OrderItems;
import com.keycloak.restaurant.repositories.OrderItemsRepo;
import com.keycloak.restaurant.repositories.OrderRepo;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	OrderItemsRepo orderItemsRepo;
	
	@GetMapping
	@RequestMapping("/{restaurantId}/list")
	// manager can access (ram)
	public List<Order> getOrders(@PathVariable Long restaurantId) {
		return orderRepo.findByRestaurantId(restaurantId);
    }
	
	@GetMapping
	@RequestMapping("/{orderId}")
	// manager can access (ram)
	public Order getOrderDetails(@PathVariable Long orderId) {
		Order order = orderRepo.findById(orderId).get();
        order.setOrderItems(orderItemsRepo.findByOrderId(order.getId()));
        return order;
    }
	
	@PostMapping
	// authenticated users can access
	public Order createOrder(Order order) {
		orderRepo.save(order);
        List<OrderItems> orderItems = order.getOrderItems();
        orderItems.forEach(orderItem -> {
            orderItem.setOrderId(order.id);
            orderItemsRepo.save(orderItem);
        });
        return order;
    }

}
