package com.keycloak.restaurant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keycloak.restaurant.entities.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long>{
    List<Order> findByRestaurantId(Long restaurantId);
}
