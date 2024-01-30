package com.keycloak.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keycloak.restaurant.entities.Restaurant;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Long>{
    
}
