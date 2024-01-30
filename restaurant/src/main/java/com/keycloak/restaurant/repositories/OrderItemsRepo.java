package com.keycloak.restaurant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keycloak.restaurant.entities.OrderItems;

@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItems,Long>{
    List<OrderItems> findByOrderId(Long id);
}
