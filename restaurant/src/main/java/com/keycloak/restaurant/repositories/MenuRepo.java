package com.keycloak.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keycloak.restaurant.entities.Menu;

@Repository
public interface MenuRepo extends JpaRepository<Menu , Long>{
    Menu findByRestaurantId(Long restaurantId);
}
